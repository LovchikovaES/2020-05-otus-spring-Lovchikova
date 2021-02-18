package ru.otus.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BookController {

    public BookController() {
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
