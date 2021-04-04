package ru.otus.spring.repository.h2;

import ru.otus.spring.model.h2.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findBookById(long id);

    List<Book> findAll();

    Book save(Book book);

    void delete(Book book);
}
