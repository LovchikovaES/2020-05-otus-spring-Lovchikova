package ru.otus.spring.dao;

import ru.otus.spring.model.Genre;

public interface GenreDao {

    Genre getById(long id);
}
