package ru.otus.spring.service;

import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface DbServiceBook {
    List<BookDto> getAllBooks();

    Optional<BookDto> getBookById(long id);

    Optional<Book> deleteBookById(long id);

    Optional<Book> createBook(String bookName,
                              List<Long> authorIds,
                              long genreId);

    Optional<Book> updateBook(long bookId,
                              List<Long> newAuthorIds,
                              long newGenreId,
                              String newBookName);
}
