package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Comment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Comment должен")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    private final static long BOOK_ID_TO_SELECT_ALL_COMMENTS = 1;
    private final static long COMMENT_ID_TO_UPDATE = 2;
    private final static long COMMENT_ID_TO_DELETE = 3;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @Test
    @DisplayName("находить все комментарии для книги")
    void shouldFindAllComments() {
        Comment comment1 = new Comment();
        comment1.setId(1);
        Comment comment2 = new Comment();
        comment2.setId(2);
        Comment comment3 = new Comment();
        comment3.setId(3);

        List<Comment> commentsExpected = Arrays.asList(comment1, comment2, comment3);
        List<Comment> commentsActual = commentRepositoryJpa.findAllForBookId(BOOK_ID_TO_SELECT_ALL_COMMENTS);
        assertThat(commentsActual).containsExactlyInAnyOrderElementsOf(commentsExpected);
    }

    @Test
    @DisplayName("обновлять комментарий")
    void shouldUpdateComment() {
        Comment expectedComment = commentRepositoryJpa.findCommentById(COMMENT_ID_TO_UPDATE).orElseThrow();
        expectedComment.setReview("Great book");
        commentRepositoryJpa.save(expectedComment);
        Comment actualComment = commentRepositoryJpa.findCommentById(COMMENT_ID_TO_UPDATE).orElseThrow();
        assertThat(actualComment.getReview()).isEqualTo(expectedComment.getReview());
    }

    @Test
    @DisplayName("добавлять комментарий")
    void shouldAddComment() {
        Comment expectedComment = new Comment();
        expectedComment.setReview("Interesting book!");
        var addedComment = commentRepositoryJpa.save(expectedComment);
        var actualComment = commentRepositoryJpa.findCommentById(addedComment.getId()).orElseThrow();
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("удалять комментарий")
    void shouldDeleteComment() {
        var commentToDelete = commentRepositoryJpa.findCommentById(COMMENT_ID_TO_DELETE).orElseThrow();
        commentRepositoryJpa.delete(commentToDelete);
        var actualComment = commentRepositoryJpa.findCommentById(COMMENT_ID_TO_DELETE);
        assertThat(actualComment).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("находить комментарий по id")
    void shouldFindCommentById() {
        Comment expectedComment = new Comment();
        expectedComment.setId(5);
        var actualComment = commentRepositoryJpa.findCommentById(expectedComment.getId()).orElseThrow();
        assertThat(actualComment).isEqualTo(expectedComment);
    }
}