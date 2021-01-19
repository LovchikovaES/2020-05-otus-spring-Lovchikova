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
        var books = dbServiceBook.getAllBooksInfo();
        for (var book : books) {
            dbServiceBook.print(book);
        }
    }

    @ShellMethod(value = "Get book by ID", key = {"gb", "getBookById"})
    public void getBookById(long id) {
        dbServiceBook.getBookInfoById(id).ifPresentOrElse(dbServiceBook::print,
                () -> ioService.output("Book not found"));
    }

    @ShellMethod(value = "Delete book by ID", key = {"db", "deleteBookById"})
    public void deleteBookById(long id) {
        if (dbServiceBook.deleteBookById(id).isEmpty()) {
            ioService.output("Book not found");
        }
    }

    @ShellMethod(value = "Create book", key = {"cb", "createBook"})
    public void createBook(String bookName,
                           long authorId,
                           long genreId) {
        dbServiceBook.createBook(authorId, genreId, bookName)
                .ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook(long bookId,
                           @ShellOption(defaultValue = "") String newName,
                           @ShellOption(arity = 2) long[] authorIds,
                           @ShellOption(defaultValue = "0") long newGenreId) {

        dbServiceBook.updateBook(bookId, authorIds, newGenreId, newName)
                .ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }
}
