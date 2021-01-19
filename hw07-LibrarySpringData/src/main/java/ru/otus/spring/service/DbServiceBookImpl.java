package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookInfo;
import ru.otus.spring.io.IOService;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbServiceBookImpl implements DbServiceBook {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    public DbServiceBookImpl(BookRepository bookRepository,
                             AuthorRepository authorRepository,
                             GenreRepository genreRepository,
                             CommentRepository commentRepository,
                             IOService ioService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.ioService = ioService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookInfo> getAllBooksInfo() {
        List<BookInfo> bookInfoList = new ArrayList<>();
        var books = bookRepository.findAll();
        for (var book : books) {
            bookInfoList.add(convertBookToBookInfo(book));
        }
        return bookInfoList;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookInfo> getBookInfoById(long id) {
        var bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convertBookToBookInfo(bookOptional.get()));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Book> deleteBookById(long id) {
        var bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        commentRepository.deleteInBatch(commentRepository.findAllByBookId(id));

        var book = bookOptional.get();
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Transactional
    @Override
    public Optional<Book> updateBook(long bookId,
                                     long[] newAuthorIds,
                                     long newGenreId,
                                     String newBookName) {
        var bookToUpdateOptional = bookRepository.findById(bookId);
        if (bookToUpdateOptional.isEmpty()) {
            return Optional.empty();
        }

        return bookToUpdateOptional.map(b -> {
            if (!newBookName.isEmpty()) {
                b.setName(newBookName);
            }

            if (newAuthorIds.length > 0) {
                b.clearAuthors();
                for (var newAuthorId : newAuthorIds) {
                    authorRepository.findById(newAuthorId).ifPresent(b::addAuthor);
                }
            }

            genreRepository.findById(newGenreId).ifPresent(b::setGenre);
            return bookRepository.save(b);
        }).or(Optional::empty);
    }

    @Transactional
    @Override
    public Optional<Book> createBook(long newAuthorId,
                                     long newGenreId,
                                     String newBookName) {
        Book bookToCreate = new Book();
        bookToCreate.setName(newBookName);
        authorRepository.findById(newAuthorId).ifPresent(bookToCreate::addAuthor);
        genreRepository.findById(newGenreId).ifPresent(bookToCreate::setGenre);

        return Optional.ofNullable(bookRepository.save(bookToCreate));
    }

    @Override
    public void print(BookInfo book) {
        ioService.output(book);
    }

    private BookInfo convertBookToBookInfo(Book book) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(book.getId());
        List<Author> authors = new ArrayList<>(book.getAuthors());
        bookInfo.setAuthors(authors);
        bookInfo.setGenre(book.getGenre());
        bookInfo.setName(book.getName());
        return bookInfo;
    }
}
