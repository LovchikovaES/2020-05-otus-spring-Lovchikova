package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
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
            ioService.output(book);
        }
    }

    @ShellMethod(value = "Get book by ID", key = {"gb", "getBookById"})
    public void getBookById() {
        ioService.output("Insert ID: ");
        long id = Long.parseLong(ioService.get());
        dbServiceBook.getBookInfoById(id).ifPresentOrElse(ioService::output,
                () -> ioService.output("Book not found"));
    }

    @ShellMethod(value = "Delete book by ID", key = {"db", "deleteBookById"})
    public void deleteBookById() {
        ioService.output("Insert ID to delete: ");
        long id = Long.parseLong(ioService.get());
        if (dbServiceBook.deleteBookById(id).isEmpty()) {
            ioService.output("Book not found");
        }
    }

    @ShellMethod(value = "Create book", key = {"cb", "createBook"})
    public void createBook() {
        ioService.output("Insert book name: ");
        String name = ioService.get();
        ioService.output("Insert author ID: ");
        long authorId = Long.parseLong(ioService.get());
        ioService.output("Insert genre ID: ");
        long genreId = Long.parseLong(ioService.get());
        dbServiceBook.createBook(authorId, genreId, name)
                .ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook() {
        ioService.output("Insert ID of updated book: ");
        var bookId = Long.parseLong(ioService.get());

        ioService.output("Insert new book name: ");
        var newName = ioService.get();

        ioService.output("Insert new author ID: ");
        var newAuthorId = Long.parseLong(ioService.get());

        ioService.output("Insert new genre ID: ");
        var newGenreId = Long.parseLong(ioService.get());

        dbServiceBook.updateBook(bookId, newAuthorId, newGenreId, newName)
                .ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }
}
