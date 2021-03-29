package ru.otus.spring.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.DbServiceAuthor;
import ru.otus.spring.service.DbServiceBook;
import ru.otus.spring.service.DbServiceGenre;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    DbServiceBook dbServiceBook;
    @MockBean
    DbServiceGenre dbServiceGenre;
    @MockBean
    DbServiceAuthor dbServiceAuthor;
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "admin",
            authorities = {"WRITE", "READ"}
    )
    @Test
    public void returnOkForAdminIfGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1L);
        Mockito.when(dbServiceBook.getBookById(1)).thenReturn(Optional.of(book));
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "user",
            authorities = {"READ"}
    )
    @Test
    public void returnForbiddenForUserIfGetBookById() throws Exception {
        Book book = new Book();
        book.setId(2L);
        Mockito.when(dbServiceBook.getBookById(2)).thenReturn(Optional.of(book));
        mockMvc.perform(get("/books/2"))
                .andExpect(status().isForbidden());
    }
}