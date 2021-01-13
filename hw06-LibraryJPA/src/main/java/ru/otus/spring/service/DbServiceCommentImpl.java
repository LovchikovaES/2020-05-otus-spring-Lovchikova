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

    @Transactional()
    @Override
    public Optional<Comment> addComment(long bookId, String comment) {
        var bookOptional = bookRepository.findBookById(bookId);
        if (bookOptional.isEmpty()) {
            return Optional.empty();
        }
        Comment newComment = new Comment(comment);
        newComment.setBook(bookOptional.get());
        return Optional.ofNullable(commentRepository.save(newComment));
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsForBook(long bookId) {
        return commentRepository.findAllForBookId(bookId);
    }
}
