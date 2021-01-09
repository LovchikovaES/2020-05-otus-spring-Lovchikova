package ru.otus.spring.dao;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(long id);

    List<Author> getAll();
}
