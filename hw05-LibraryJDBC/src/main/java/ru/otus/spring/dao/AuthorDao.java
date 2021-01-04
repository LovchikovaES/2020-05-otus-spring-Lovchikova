package ru.otus.spring.dao;

import ru.otus.spring.model.Author;

public interface AuthorDao {

    Author getById(long id);
}
