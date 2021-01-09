package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.io.IOService;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

@ShellComponent
public class BookEventsCommands {

    private final BookDao bookDao;
    private final IOService ioService;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookEventsCommands(
            BookDao bookDao,
            IOService ioService,
            AuthorDao authorDao,
            GenreDao genreDao) {
        this.bookDao = bookDao;
        this.ioService = ioService;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Get all books", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        var books = bookDao.getAll();
        for (var book : books) {
            ioService.output(book);
        }
    }

    @ShellMethod(value = "Get book by ID", key = {"gb", "getBookById"})
    public void getBookById() {
        ioService.output("Insert ID: ");
        long id = Long.parseLong(ioService.get());
        ioService.output(bookDao.getById(id));
    }

    @ShellMethod(value = "Delete book by ID", key = {"db", "deleteBookById"})
    public void deleteBookById() {
        ioService.output("Insert ID to delete: ");
        long id = Long.parseLong(ioService.get());
        bookDao.deleteById(id);
    }

    @ShellMethod(value = "Create book", key = {"cb", "createBook"})
    public void createBook() {
        ioService.output("Insert book name: ");
        String name = ioService.get();
        ioService.output("Insert author ID: ");
        long authorId = Long.parseLong(ioService.get());
        Author author = authorDao.getById(authorId);
        ioService.output("Insert genre ID: ");
        long genreId = Long.parseLong(ioService.get());
        Genre genre = genreDao.getById(genreId);
        var book = new Book(name);
        book.setAuthor(author);
        book.setGenre(genre);
        bookDao.insert(book);
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook() {
        ioService.output("Insert ID of updated book: ");
        var updatedBook = bookDao.getById(Long.parseLong(ioService.get()));
        ioService.output("Insert new book name: ");
        updatedBook.setName(ioService.get());
        ioService.output("Insert new author ID: ");
        updatedBook.setAuthor(authorDao.getById((Long.parseLong(ioService.get()))));
        ioService.output("Insert new genre ID: ");
        updatedBook.setGenre(genreDao.getById(Long.parseLong(ioService.get())));
        bookDao.update(updatedBook);
    }
}
