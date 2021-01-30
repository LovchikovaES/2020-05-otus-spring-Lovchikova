package ru.otus.spring.service;

import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface DbServiceComment {
    Optional<Comment> addComment(String bookId, String comment);

    List<Comment> getCommentsForBook(String bookId);

    void deleteCommentsForBook(String bookId);

    Optional<Comment> deleteComment(String id);
}
