package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(long bookId);

    Comment save(Comment comment);

    void delete(Comment comment);

    void deleteInBatch(Iterable<Comment> comments);
}
