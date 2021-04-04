package ru.otus.spring.repository.h2;

import ru.otus.spring.model.h2.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findCommentById(long id);

    List<Comment> findAllForBookId(long bookId);

    Comment save(Comment comment);

    void delete(Comment comment);
}
