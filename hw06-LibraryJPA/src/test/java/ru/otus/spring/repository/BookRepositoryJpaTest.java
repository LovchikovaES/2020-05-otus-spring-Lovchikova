package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Book должен")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private final static long BOOK_ID_TO_UPDATE = 2;
    private final static long BOOK_ID_TO_DELETE = 2;
    private final static long BOOK_ID_TO_SELECT = 1;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Test
    @DisplayName("находить все книги")
    void shouldFindAllBooks() {
        Book book1 = new Book();
        book1.setId(1);
        Book book2 = new Book();
        book2.setId(2);
        Book book3 = new Book();
        book3.setId(3);
        List<Book> booksExpected = Arrays.asList(book1, book2, book3);
        List<Book> booksActual = bookRepositoryJpa.findAll();
        assertThat(booksActual).containsExactlyInAnyOrderElementsOf(booksExpected);
    }

    @Test
    @DisplayName("обновлять название книги")
    void shouldUpdateBook() {
        Book expectedBook = bookRepositoryJpa.findBookById(BOOK_ID_TO_UPDATE).orElseThrow();
        expectedBook.setName("Book Name Test");
        bookRepositoryJpa.save(expectedBook);
        Book actualBook = bookRepositoryJpa.findBookById(BOOK_ID_TO_UPDATE).orElseThrow();
        assertThat(actualBook.getName()).isEqualTo(expectedBook.getName());
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() {
        Book expectedBook = new Book();
        expectedBook.setName("Book 2");
        var newBook = bookRepositoryJpa.save(expectedBook);
        Book actualBook = bookRepositoryJpa.findBookById(newBook.getId()).orElseThrow();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() {
        var bookToDelete = bookRepositoryJpa.findBookById(BOOK_ID_TO_DELETE).orElseThrow();
        bookRepositoryJpa.delete(bookToDelete);
        var actualBook = bookRepositoryJpa.findBookById(BOOK_ID_TO_DELETE);
        assertThat(actualBook).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("находить книгу по id")
    void shouldFindBookById() {
        Book expectedBook = new Book();
        expectedBook.setId(BOOK_ID_TO_SELECT);
        expectedBook.setName("Island of the Lost");
        var actualBook = bookRepositoryJpa.findBookById(expectedBook.getId()).orElseThrow();
        assertThat(actualBook.getName()).isEqualTo(expectedBook.getName());
        assertThat(actualBook.getId()).isEqualTo(expectedBook.getId());
    }
}