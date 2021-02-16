package ru.otus.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.DbServiceAuthor;

import java.util.List;

@RestController
public class AuthorRestController {
    final private DbServiceAuthor dbServiceAuthor;

    public AuthorRestController(DbServiceAuthor dbServiceAuthor) {
        this.dbServiceAuthor = dbServiceAuthor;
    }

    @GetMapping("/api/authors")
    public List<Author> getAllAuthors() {
        return dbServiceAuthor.getAllAuthors();
    }

}
