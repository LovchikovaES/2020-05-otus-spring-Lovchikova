package ru.otus.spring.repository.h2;

import ru.otus.spring.model.h2.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findAuthorById(long id);

    List<Author> findAll();

    Author save(Author author);

    void delete(Author author);
}
