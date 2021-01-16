package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookInfo;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbServiceBookImpl implements DbServiceBook {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public DbServiceBookImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
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
        var bookOptional = bookRepository.findBookById(id);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convertBookToBookInfo(bookOptional.get()));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.findBookById(id);
    }

    @Transactional
    @Override
    public Optional<Book> deleteBookById(long id) {
        var bookOptional = bookRepository.findBookById(id);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        var book = bookOptional.get();
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Transactional
    @Override
    public Optional<Book> updateBook(long bookId,
                                     long newAuthorId,
                                     long newGenreId,
                                     String newBookName) {
        var bookToUpdateOptional = bookRepository.findBookById(bookId);
        if (bookToUpdateOptional.isEmpty()) {
            return Optional.empty();
        }
        var bookToUpdate = bookToUpdateOptional.get();

        if (!newBookName.isEmpty()) {
            bookToUpdate.setName(newBookName);
        }

        if (newAuthorId > 0) {
            bookToUpdate.clearAuthors();
            authorRepository.findAuthorById(newAuthorId)
                    .ifPresent(bookToUpdate::addAuthor);
        }

        if (newGenreId > 0) {
            genreRepository.findGenreById(newGenreId)
                    .ifPresent(bookToUpdate::setGenre);
        }
        return Optional.ofNullable(bookRepository.save(bookToUpdate));
    }

    @Transactional
    @Override
    public Optional<Book> createBook(long newAuthorId,
                                     long newGenreId,
                                     String newBookName) {
        Book bookToCreate = new Book();
        bookToCreate.setName(newBookName);

        if (newAuthorId > 0) {
            authorRepository.findAuthorById(newAuthorId)
                    .ifPresent(bookToCreate::addAuthor);
        }

        if (newGenreId > 0) {
            genreRepository.findGenreById(newGenreId)
                    .ifPresent(bookToCreate::setGenre);
        }
        return Optional.ofNullable(bookRepository.save(bookToCreate));
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
