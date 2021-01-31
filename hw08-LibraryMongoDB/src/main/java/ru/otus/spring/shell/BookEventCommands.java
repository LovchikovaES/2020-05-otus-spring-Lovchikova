package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.io.IOService;
import ru.otus.spring.service.DbServiceBook;

@ShellComponent
public class BookEventCommands {

    private final IOService ioService;
    private final DbServiceBook dbServiceBook;

    public BookEventCommands(
            IOService ioService,
            DbServiceBook dbServiceBook) {
        this.ioService = ioService;
        this.dbServiceBook = dbServiceBook;
    }

    @ShellMethod(value = "Get all books", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        var books = dbServiceBook.getAllBooks();
        for (var book : books) {
            dbServiceBook.print(book);
        }
    }

    @ShellMethod(value = "Get book by ID", key = {"gb", "getBookById"})
    public void getBookById(String id) {
        dbServiceBook.getBookById(id).ifPresentOrElse(dbServiceBook::print,
                () -> ioService.output("Book not found"));
    }

    @ShellMethod(value = "Delete book by ID", key = {"db", "deleteBookById"})
    public void deleteBookById(String id) {
        if (dbServiceBook.deleteBookById(id).isEmpty()) {
            ioService.output("Book not found");
        }
    }

    @ShellMethod(value = "Create book", key = {"cb", "createBook"})
    public void createBook(String bookName,
                           String authorId,
                           String genre) {
        dbServiceBook.createBook(authorId, genre, bookName)
                .ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook(String bookId,
                           @ShellOption(defaultValue = "") String newName,
                           @ShellOption(arity = 2) String[] authorIds,
                           @ShellOption(defaultValue = "") String newGenre) {

        dbServiceBook.updateBook(bookId, authorIds, newGenre, newName)
                .ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }
}
