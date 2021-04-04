package ru.otus.spring.repository.h2;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.h2.Author;
import ru.otus.spring.model.h2.Comment;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Comment> findCommentById(long id) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.id = :id",
                Comment.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Comment> findAllForBookId(long bookId) {
        var query = em.createQuery("select c from Comment c join c.book b where b.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void delete(Comment comment) {
        if (comment == null || comment.getId() == 0) {
            return;
        }
        var existing = (Author) em.find(Author.class, comment.getId());

        if (existing == null) {
            return;
        }

        em.remove(em.contains(comment) ? comment : em.merge(comment));
    }
}
