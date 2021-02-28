package ru.otus.spring.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;

@RestController
public class BookRestController {
    final private BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    @Transactional(readOnly = true)
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/api/books/{bookId}")
    @Transactional(readOnly = true)
    public Mono<Book> showBookById(@PathVariable(name = "bookId") String bookId) {
        return bookRepository.findById(bookId);
    }

    @DeleteMapping("/api/books/{bookId}")
    @Transactional
    public void deleteBook(@PathVariable(name = "bookId") String bookId) {
        bookRepository.deleteById(bookId).subscribe();
    }

    @PostMapping("/api/books")
    @Transactional
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book).subscribe();
    }

    @Transactional
    @PutMapping("/api/books/{bookId}")
    public void updateBook(@RequestBody Book book) {
        bookRepository.save(book).subscribe();
    }

}
