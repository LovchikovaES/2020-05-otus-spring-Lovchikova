package ru.otus.spring.dao;

import ru.otus.spring.model.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    void update(Book book);
}
