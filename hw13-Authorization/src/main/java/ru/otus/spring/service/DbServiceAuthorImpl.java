package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Author;
import ru.otus.spring.repository.AuthorRepository;

import java.util.List;

@Service
public class DbServiceAuthorImpl implements DbServiceAuthor {

    private final AuthorRepository authorRepository;

    public DbServiceAuthorImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
