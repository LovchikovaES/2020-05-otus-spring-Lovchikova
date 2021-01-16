package ru.otus.spring.service;

import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface DbServiceComment {
    Optional<Comment> addComment(long bookId, String comment);

    List<Comment> getCommentsForBook(long bookId);
}
