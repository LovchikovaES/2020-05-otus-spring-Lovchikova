package ru.otus.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.DbServiceGenre;

import java.util.List;

@RestController
public class GenreRestController {
    final private DbServiceGenre dbServiceGenre;

    public GenreRestController(DbServiceGenre dbServiceGenre) {
        this.dbServiceGenre = dbServiceGenre;
    }

    @GetMapping("/api/genres")
    public List<Genre> getAllGenres() {
        return dbServiceGenre.getAllGenres();
    }

}
