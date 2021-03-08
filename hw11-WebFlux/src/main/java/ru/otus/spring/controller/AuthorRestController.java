package ru.otus.spring.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.model.Author;
import ru.otus.spring.repository.AuthorRepository;

@RestController
public class AuthorRestController {
    final private AuthorRepository authorRepository;

    public AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/api/authors")
    @Transactional(readOnly = true)
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
