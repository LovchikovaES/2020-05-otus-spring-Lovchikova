package ru.otus.spring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.model.h2.Author;
import ru.otus.spring.model.h2.Book;
import ru.otus.spring.model.h2.Genre;
import ru.otus.spring.repository.h2.AuthorRepository;
import ru.otus.spring.repository.h2.BookRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class JobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private AuthorRepository authorRepositoryH2;

    @Autowired
    private ru.otus.spring.repository.mongo.AuthorRepository authorRepositoryMongo;

    @Autowired
    private BookRepository bookRepositoryH2;

    @Autowired
    private ru.otus.spring.repository.mongo.BookRepository bookRepositoryMongo;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void shouldMigrateAuthors() {

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("migrateAuthors");
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        var expectedAuthors = authorRepositoryH2.findAll();
        List<Author> actualAuthors = new ArrayList<>();

        var authorsMongo = authorRepositoryMongo.findAll();
        for (var authorMongo : authorsMongo) {
            var authorH2 = new Author();
            authorH2.setName(authorMongo.getName());
            actualAuthors.add(authorH2);
        }
        assertThat(actualAuthors)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedAuthors);
    }

    @Test
    void shouldMigrateBooks() {

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("migrateAuthors");
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        jobExecution = jobLauncherTestUtils.launchStep("migrateBooks");
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        var expectedBooks = bookRepositoryH2.findAll();
        List<Book> actualBooks = new ArrayList<>();

        var booksMongo = bookRepositoryMongo.findAll();
        for (var bookMongo : booksMongo) {
            var bookH2 = new Book();
            bookH2.setName(bookMongo.getName());

            var genre = new Genre();
            genre.setName(bookMongo.getGenre());
            bookH2.setGenre(genre);

            List<Author> authors = new ArrayList<>();
            for (var authorMongo : bookMongo.getAuthors()) {
                Author author = new Author();
                author.setName(authorMongo.getName());
                authors.add(author);
            }
            bookH2.setAuthors(authors);
            actualBooks.add(bookH2);
        }

        assertThat(actualBooks)
                .usingRecursiveComparison()
                .ignoringFields("id", "genre.id", "authors.id")
                .isEqualTo(expectedBooks);
    }
}