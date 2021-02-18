package ru.otus.spring.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.service.DbServiceBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookRestController {
    final private DbServiceBook dbServiceBook;

    public BookRestController(DbServiceBook dbServiceBook) {
        this.dbServiceBook = dbServiceBook;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return dbServiceBook.getAllBooks();
    }

    @GetMapping("/api/books/{bookId}")
    public BookDto showBookById(@PathVariable(name = "bookId") Long bookId) {
        Optional<BookDto> book = dbServiceBook.getBookById(bookId);
        return book.orElse(null);
    }

    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable(name = "bookId") Long bookId) {
        dbServiceBook.deleteBookById(bookId);
    }

    @PostMapping("/api/books")
    public void createBook(@RequestBody BookDto book) {
        List<Long> authorIds = new ArrayList<>();

        for (var author : book.getAuthors()) {
            authorIds.add(author.getId());
        }

        Long genreId = 0L;
        if (book.getGenre() != null) {
            genreId = book.getGenre().getId();
        }
        dbServiceBook.createBook(book.getName(), authorIds, genreId);
    }

    @PutMapping("/api/books/{bookId}")
    public void updateBook(@RequestBody BookDto book) {
        List<Long> authorIds = new ArrayList<>();
        for (var author : book.getAuthors()) {
            authorIds.add(author.getId());
        }
        Long genreId = 0L;
        if (book.getGenre() != null) {
            genreId = book.getGenre().getId();
        }
        dbServiceBook.updateBook(book.getId(), authorIds, genreId, book.getName());
    }

}
