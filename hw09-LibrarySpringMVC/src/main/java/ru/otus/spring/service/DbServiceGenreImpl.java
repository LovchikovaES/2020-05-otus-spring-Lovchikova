package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Genre;
import ru.otus.spring.repository.GenreRepository;

import java.util.List;

@Service
public class DbServiceGenreImpl implements DbServiceGenre {

    private final GenreRepository genreRepository;

    public DbServiceGenreImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
