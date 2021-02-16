package ru.otus.spring.service;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface DbServiceGenre {
    List<Genre> getAllGenres();
}
