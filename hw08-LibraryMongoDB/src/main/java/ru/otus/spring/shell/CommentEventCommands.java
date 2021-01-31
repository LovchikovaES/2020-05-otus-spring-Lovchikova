package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.io.IOService;
import ru.otus.spring.service.DbServiceComment;

@ShellComponent
public class CommentEventCommands {

    private final IOService ioService;
    private final DbServiceComment dbServiceComment;

    public CommentEventCommands(IOService ioService,
                                DbServiceComment dbServiceComment) {
        this.ioService = ioService;
        this.dbServiceComment = dbServiceComment;
    }

    @ShellMethod(value = "Get comments for book", key = {"gbc", "getBookComments"})
    public void getCommentsForBook(String bookId) {
        dbServiceComment.getCommentsForBook(bookId).forEach(ioService::output);
    }

    @ShellMethod(value = "Add comment", key = {"ac", "addComment"})
    public void addCommentForBook(String bookId, String comment) {
        dbServiceComment.addComment(bookId, comment).ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }

    @ShellMethod(value = "Delete comments for book", key = {"dbc", "deleteBookComments"})
    public void deleteCommentsForBook(String bookId) {
        dbServiceComment.deleteCommentsForBook(bookId);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public void deleteComment(String id) {
        dbServiceComment.deleteComment(id).ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }
}
