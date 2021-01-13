package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Author должен")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    private final static long AUTHOR_ID_TO_UPDATE = 2;
    private final static long AUTHOR_ID_TO_DELETE = 4;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Test
    @DisplayName("находить всех авторов")
    void shouldFindAllAuthors() {
        Author author1 = new Author(1, "Joan Druett");
        Author author2 = new Author(2, "Lee Child");
        Author author3 = new Author(3, "Andrew Child");
        Author author4 = new Author(4, "Ray Bradbury");

        List<Author> authorsExpected = Arrays.asList(author1, author2, author3, author4);
        List<Author> authorsActual = authorRepositoryJpa.findAll();
        assertThat(authorsActual).containsExactlyInAnyOrderElementsOf(authorsExpected);
    }

    @Test
    @DisplayName("обновлять автора")
    void shouldUpdateAuthor() {
        Author expectedAuthor = authorRepositoryJpa.findAuthorById(AUTHOR_ID_TO_UPDATE).orElseThrow();
        expectedAuthor.setName("test test");
        authorRepositoryJpa.save(expectedAuthor);
        Author actualAuthor = authorRepositoryJpa.findAuthorById(AUTHOR_ID_TO_UPDATE).orElseThrow();
        assertThat(actualAuthor.getName()).isEqualTo(expectedAuthor.getName());
    }

    @Test
    @DisplayName("добавлять автора")
    void shouldAddAuthor() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName("Author 2");
        var newAuthor = authorRepositoryJpa.save(expectedAuthor);
        Author actualAuthor = authorRepositoryJpa.findAuthorById(newAuthor.getId()).orElseThrow();
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("удалять автора")
    void shouldDeleteAuthor() {
        var authorToDelete = authorRepositoryJpa.findAuthorById(AUTHOR_ID_TO_DELETE).orElseThrow();
        authorRepositoryJpa.delete(authorToDelete);
        var actualAuthor = authorRepositoryJpa.findAuthorById(AUTHOR_ID_TO_DELETE);
        assertThat(actualAuthor).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("находить автора по id")
    void shouldFindAuthorById() {
        Author expectedAuthor = new Author(1, "Joan Druett");
        var actualAuthor = authorRepositoryJpa.findAuthorById(expectedAuthor.getId()).orElseThrow();
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}