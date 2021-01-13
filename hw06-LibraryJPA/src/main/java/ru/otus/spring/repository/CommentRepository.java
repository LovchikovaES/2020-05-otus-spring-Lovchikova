package ru.otus.spring.repository;

import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findCommentById(long id);

    List<Comment> findAllForBookId(long bookId);

    Comment save(Comment comment);

    void delete(Comment comment);
}
