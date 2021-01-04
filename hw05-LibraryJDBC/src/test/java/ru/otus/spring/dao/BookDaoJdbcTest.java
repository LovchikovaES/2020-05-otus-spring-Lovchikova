package ru.otus.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DAO для работы с книгами")
@JdbcTest
@Import(BookDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcTest {

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    @DisplayName("исключение если id не найден")
    void shouldReturnExceptionWhenIdNotFound() {
        final int notExistBookId = 8;
        assertThatThrownBy(() -> bookDao.getById(notExistBookId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("возвращает книгу по id")
    void shouldReturnBookWhenGetById() {
        Book expectedBook = new Book(1, "Sherlock Holmes", new Author(1), new Genre(3));
        Mockito.when(authorDao.getById(1)).thenReturn(new Author(1));
        Mockito.when(genreDao.getById(3)).thenReturn(new Genre(3));
        var actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("удаляет книгу по id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteBookById() {
        final int deletedBookId = 1;
        bookDao.deleteById(deletedBookId);
        assertThatThrownBy(() -> bookDao.getById(deletedBookId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("возвращает все книги")
    void shouldReturnAllBooks() {
        Mockito.when(authorDao.getById(Mockito.anyLong())).thenAnswer((Answer<Author>) invocationOnMock -> {
            long id = invocationOnMock.getArgument(0);
            return new Author(id);
        });
        Mockito.when(genreDao.getById(Mockito.anyLong())).thenAnswer((Answer<Genre>) invocationOnMock -> {
            long id = invocationOnMock.getArgument(0);
            return new Genre(id);
        });

        List<Book> expectedBooks = Arrays.asList(
                new Book(1, "Sherlock Holmes", new Author(1), new Genre(3)),
                new Book(2, "Oliver Twist", new Author(2), new Genre(2)),
                new Book(3, "Blue Moon", new Author(3), new Genre(1)));

        var books = bookDao.getAll();
        assertThat(books).containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @Test
    @DisplayName("обновляет название книги, автора и жанр")
    void shouldUpdateBook() {
        final String newName = "Test name";
        final long newAuthorId = 3L;
        final long newGenreId = 1L;

        Mockito.when(authorDao.getById(Mockito.anyLong())).thenAnswer((Answer<Author>) invocationOnMock -> {
            long id = invocationOnMock.getArgument(0);
            return new Author(id);
        });
        Mockito.when(genreDao.getById(Mockito.anyLong())).thenAnswer((Answer<Genre>) invocationOnMock -> {
            long id = invocationOnMock.getArgument(0);
            return new Genre(id);
        });

        var expectedBook = bookDao.getById(2);
        expectedBook.setName(newName);
        expectedBook.setGenre(new Genre(newGenreId));
        expectedBook.setAuthor(new Author(newAuthorId));
        bookDao.update(expectedBook);

        var actualBook = bookDao.getById(2);
        Assertions.assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        Assertions.assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
        Assertions.assertEquals(actualBook.getName(), expectedBook.getName());
    }

}