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

    @ShellMethod(value = "Get comments for book", key = {"gc", "getComments"})
    public void getCommentsForBook(long id) {
        dbServiceComment.getCommentsForBook(id).forEach(ioService::output);
    }

    @ShellMethod(value = "Add comment", key = {"ac", "addComment"})
    public void addCommentForBook(long id, String comment) {
        dbServiceComment.addComment(id, comment).ifPresentOrElse(ioService::output, () -> ioService.output("error"));
    }
}
