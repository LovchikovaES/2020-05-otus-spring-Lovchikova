package ru.otus.spring.service;

import ru.otus.spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface DbServiceBook {
    List<Book> getAllBooks();

    Optional<Book> getBookById(String id);

    Optional<Book> deleteBookById(String id);

    Optional<Book> createBook(String newAuthorId,
                              String newGenre,
                              String newBookName);

    Optional<Book> updateBook(String bookId,
                              String[] newAuthorIds,
                              String newGenre,
                              String newBookName);

    void print(Book book);
}
