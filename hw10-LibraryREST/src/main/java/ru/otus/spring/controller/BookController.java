package ru.otus.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.service.DbServiceAuthor;
import ru.otus.spring.service.DbServiceBook;
import ru.otus.spring.service.DbServiceGenre;


@Controller
public class BookController {

    final private DbServiceBook dbServiceBook;
    final private DbServiceGenre dbServiceGenre;
    final private DbServiceAuthor dbServiceAuthor;

    public BookController(DbServiceBook dbServiceBook, DbServiceGenre dbServiceGenre, DbServiceAuthor dbServiceAuthor) {
        this.dbServiceBook = dbServiceBook;
        this.dbServiceGenre = dbServiceGenre;
        this.dbServiceAuthor = dbServiceAuthor;
    }

    @GetMapping("/books")
    public String showAllBooks() {
        return "books";
    }

    @GetMapping("/newbook")
    public String createNewBook() {
        return "newbook";
    }

    @GetMapping("/books/{bookId}")
    public String showBookById(@PathVariable(name = "bookId") Long bookId) {
        return "book";
    }
}
