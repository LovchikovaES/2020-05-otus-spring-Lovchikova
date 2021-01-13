package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Genre должен")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    private final static long GENRE_ID_TO_UPDATE = 2;
    private final static long GENRE_ID_TO_DELETE = 3;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Test
    @DisplayName("находить все жанры")
    void shouldFindAllGenres() {
        Genre genre1 = new Genre(1, "adventure");
        Genre genre2 = new Genre(2, "classics");
        Genre genre3 = new Genre(3, "fantasy");
        List<Genre> genresExpected = Arrays.asList(genre1, genre2, genre3);
        List<Genre> genresActual = genreRepositoryJpa.findAll();
        assertThat(genresActual).containsExactlyInAnyOrderElementsOf(genresExpected);
    }

    @Test
    @DisplayName("обновлять жанр")
    void shouldUpdateGenre() {
        Genre expectedGenre = genreRepositoryJpa.findGenreById(GENRE_ID_TO_UPDATE).orElseThrow();
        expectedGenre.setName("detective");
        genreRepositoryJpa.save(expectedGenre);
        Genre actualGenre = genreRepositoryJpa.findGenreById(GENRE_ID_TO_UPDATE).orElseThrow();
        assertThat(actualGenre.getName()).isEqualTo(expectedGenre.getName());
    }

    @Test
    @DisplayName("добавлять жанр")
    void shouldAddGenre() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName("biography");
        var newGenre = genreRepositoryJpa.save(expectedGenre);
        Genre actualGenre = genreRepositoryJpa.findGenreById(newGenre.getId()).orElseThrow();
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("удалять жанр")
    void shouldDeleteGenre() {
        var genreToDelete = genreRepositoryJpa.findGenreById(GENRE_ID_TO_DELETE).orElseThrow();
        genreRepositoryJpa.delete(genreToDelete);
        var actualGenre = genreRepositoryJpa.findGenreById(GENRE_ID_TO_DELETE);
        assertThat(actualGenre).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("находить автора по id")
    void shouldFindGenreById() {
        Genre expectedGenre = new Genre(1, "adventure");
        var actualGenre = genreRepositoryJpa.findGenreById(expectedGenre.getId()).orElseThrow();
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}