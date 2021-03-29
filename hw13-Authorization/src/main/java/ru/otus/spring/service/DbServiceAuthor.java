package ru.otus.spring.service;

import ru.otus.spring.model.Author;

import java.util.List;

public interface DbServiceAuthor {
    List<Author> getAllAuthors();
}
