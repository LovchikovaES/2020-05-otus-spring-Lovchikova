package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@DisplayName("Сервис по книгам")
@ExtendWith(MockitoExtension.class)
class DbServiceBookImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DbServiceBookImpl dbServiceBook;

    private Book book1 = new Book();
    private Book book2 = new Book();
    private Book book3 = new Book();
    private BookDto bookDto1 = new BookDto();
    private BookDto bookDto2 = new BookDto();
    private BookDto bookDto3 = new BookDto();

    @BeforeEach
    void setup() {
        book1.setId(1L);
        book1.setName("book1");

        book2.setId(2L);
        book2.setName("book2");

        book3.setId(3L);
        book3.setName("book3");

        bookDto1.setId(1L);
        bookDto1.setName("book1");

        bookDto2.setId(2L);
        bookDto2.setName("book2");

        bookDto3.setId(3L);
        bookDto3.setName("book3");
    }

    @Test
    @DisplayName("должен возвращать все книги")
    void shouldReturnAllBooks() {
        List<Book> booksExpectedRepository = Arrays.asList(book1, book2, book3);
        doReturn(booksExpectedRepository).when(bookRepository).findAll();

        List<BookDto> booksExpected = Arrays.asList(bookDto1, bookDto2, bookDto3);
        List<BookDto> booksActual = dbServiceBook.getAllBooks();

        assertThat(booksActual).usingRecursiveComparison().ignoringFields("authors", "genre").isEqualTo(booksExpected);
    }

    @Test
    @DisplayName("должен возвращать книгу по ид = 1")
    void shouldReturnBookById1() {
        var bookExpected = Optional.of(bookDto1);
        doReturn(Optional.of(book1)).when(bookRepository).findById(1L);
        var bookActual = dbServiceBook.getBookById(1L);
        assertThat(bookActual).usingRecursiveComparison().ignoringFields("authors", "genre").isEqualTo(bookExpected);
    }
}