package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceCommentImpl implements DbServiceComment {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public DbServiceCommentImpl(CommentRepository commentRepository,
                                BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Optional<Comment> addComment(String bookId, String comment) {
        var bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        Comment newComment = new Comment(comment);
        newComment.setBook(bookOptional.get());
        return Optional.of(commentRepository.save(newComment));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentsForBook(String bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Transactional
    @Override
    public void deleteCommentsForBook(String bookId) {
        commentRepository.deleteAll(commentRepository.findAllByBookId(bookId));
    }

    @Transactional
    @Override
    public Optional<Comment> deleteComment(String id) {
        var commentToDelete = commentRepository.findById(id);
        if (commentToDelete.isEmpty()) {
            return Optional.empty();
        }
        commentRepository.delete(commentToDelete.get());
        return commentToDelete;
    }

}
