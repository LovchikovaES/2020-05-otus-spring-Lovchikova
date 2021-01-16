package ru.otus.spring.service;

import ru.otus.spring.dto.BookInfo;
import ru.otus.spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface DbServiceBook {
    List<BookInfo> getAllBooksInfo();

    Optional<BookInfo> getBookInfoById(long id);

    Optional<Book> getBookById(long id);

    Optional<Book> deleteBookById(long id);

    Optional<Book> createBook(long newAuthorId,
                              long newGenreId,
                              String newBookName);

    Optional<Book> updateBook(long bookId,
                              long newAuthorId,
                              long newGenreId,
                              String newBookName);
}
