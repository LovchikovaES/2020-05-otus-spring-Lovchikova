package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.io.IOService;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceBookImpl implements DbServiceBook {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    public DbServiceBookImpl(BookRepository bookRepository,
                             AuthorRepository authorRepository,
                             CommentRepository commentRepository,
                             IOService ioService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.ioService = ioService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Book> deleteBookById(String id) {
        var bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        var book = bookOptional.get();
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Transactional
    @Override
    public Optional<Book> updateBook(String bookId,
                                     String[] newAuthorIds,
                                     String newGenre,
                                     String newBookName) {
        var bookToUpdateOptional = bookRepository.findById(bookId);
        if (bookToUpdateOptional.isEmpty()) {
            return Optional.empty();
        }

        return bookToUpdateOptional.map(b -> {
            if (!newBookName.isEmpty()) {
                b.setName(newBookName);
            }

            if (!newGenre.isEmpty()) {
                b.setGenre(newGenre);
            }

            if (newAuthorIds.length > 0) {
                b.clearAuthors();
                for (var newAuthorId : newAuthorIds) {
                    authorRepository.findById(newAuthorId).ifPresent(b::addAuthor);
                }
            }

            return bookRepository.save(b);
        }).or(Optional::empty);
    }

    @Transactional
    @Override
    public Optional<Book> createBook(String newAuthorId,
                                     String newGenre,
                                     String newBookName) {
        Book bookToCreate = new Book();
        bookToCreate.setName(newBookName);
        bookToCreate.setGenre(newGenre);
        authorRepository.findById(newAuthorId).ifPresent(bookToCreate::addAuthor);

        return Optional.of(bookRepository.save(bookToCreate));
    }

    @Override
    public void print(Book book) {
        ioService.output(book);
    }
}
