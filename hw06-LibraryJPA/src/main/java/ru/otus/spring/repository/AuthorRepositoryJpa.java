package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> findAuthorById(long id) {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a where a.id = :id",
                Author.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public void delete(Author author) {
        if (author == null || author.getId() == 0) {
            return;
        }
        var existing = (Author) em.find(Author.class, author.getId());

        if (existing == null) {
            return;
        }

        em.remove(em.contains(author) ? author : em.merge(author));
    }
}
