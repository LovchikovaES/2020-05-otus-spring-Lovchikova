package ru.otus.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.DbServiceAuthor;
import ru.otus.spring.service.DbServiceBook;
import ru.otus.spring.service.DbServiceGenre;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public String showAllBooks(Model model) {
        model.addAttribute("books", dbServiceBook.getAllBooksInfo());
        return "books";
    }

    @PostMapping(path = "/books")
    public String createNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @PutMapping(value = "/books/*", params = "save")
    public RedirectView saveBook(final Book book) {
        List<Long> authorIds = new ArrayList<>();

        for (var author : book.getAuthors()) {
            authorIds.add(author.getId());
        }
        Long genreId = 0L;
        if (book.getGenre() != null) {
            genreId = book.getGenre().getId();
        }
        if (book.getId() == null) {
            dbServiceBook.createBook(book.getName(), authorIds, genreId);
        } else {
            dbServiceBook.updateBook(book.getId(), authorIds, genreId, book.getName());
        }
        return new RedirectView("/books", true);
    }

    @PutMapping(path = "/books/*", params = "addAuthor")
    public String addAuthor(final Book book) {
        book.addAuthor(new Author());
        return "book";
    }

    @PutMapping(path = "/books/*", params = "removeAuthor")
    public String removeAuthor(final Book book, final HttpServletRequest req) {
        final int authorId = Integer.parseInt(req.getParameter("removeAuthor"));
        book.deleteAuthor(authorId);
        return "book";
    }

    @DeleteMapping(path = "/books/{bookId}")
    public RedirectView deleteBook(@PathVariable(name = "bookId") Long bookId) {
        dbServiceBook.deleteBookById(bookId);
        return new RedirectView("/books", true);
    }

    @GetMapping("/books/{bookId}")
    public String showBookById(Model model, @PathVariable(name = "bookId") Long bookId) {
        Optional<BookDto> book = dbServiceBook.getBookInfoById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book";
        } else {
            return "error";
        }
    }

    @ModelAttribute("allGenres")
    public List<Genre> getAllGenres() {
        return dbServiceGenre.getAllGenres();
    }

    @ModelAttribute("allAuthors")
    public List<Author> getAllAuthors() {
        return dbServiceAuthor.getAllAuthors();
    }
}
