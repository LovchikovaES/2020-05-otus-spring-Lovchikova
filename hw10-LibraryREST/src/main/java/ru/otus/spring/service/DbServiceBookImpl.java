package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookDto;
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

    public DbServiceBookImpl(BookRepository bookRepository,
                             AuthorRepository authorRepository,
                             GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtoList = new ArrayList<>();
        var books = bookRepository.findAll();
        for (var book : books) {
            bookDtoList.add(convertBookToBookDto(book));
        }
        return bookDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookDto> getBookById(long id) {
        var bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convertBookToBookDto(bookOptional.get()));
    }

    @Transactional
    @Override
    public Optional<Book> deleteBookById(long id) {
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
    public Optional<Book> updateBook(long bookId,
                                     List<Long> newAuthorIds,
                                     long newGenreId,
                                     String newBookName) {
        var bookToUpdateOptional = bookRepository.findById(bookId);
        if (bookToUpdateOptional.isEmpty()) {
            return Optional.empty();
        }

        return bookToUpdateOptional.map(b -> {
            b.setName(newBookName);

            b.clearAuthors();
            for (var newAuthorId : newAuthorIds) {
                authorRepository.findById(newAuthorId).ifPresent(b::addAuthor);
            }

            genreRepository.findById(newGenreId).ifPresent(b::setGenre);
            return bookRepository.save(b);
        }).or(Optional::empty);
    }

    @Transactional
    @Override
    public Optional<Book> createBook(String bookName,
                                     List<Long> authorIds,
                                     long genreId) {
        List<Author> authors = new ArrayList<>();
        Book bookToCreate = new Book();
        bookToCreate.setName(bookName);
        genreRepository.findById(genreId).ifPresent(bookToCreate::setGenre);
        for (var authorId : authorIds) {
            authorRepository.findById(authorId).ifPresent(authors::add);
        }
        bookToCreate.setAuthors(authors);

        return Optional.of(bookRepository.save(bookToCreate));
    }

    private BookDto convertBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        List<Author> authors = new ArrayList<>(book.getAuthors());
        bookDto.setAuthors(authors);
        bookDto.setGenre(book.getGenre());
        bookDto.setName(book.getName());
        return bookDto;
    }
}
