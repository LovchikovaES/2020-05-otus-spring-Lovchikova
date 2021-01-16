package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> findGenreById(long id) {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g where g.id = :id",
                Genre.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public void delete(Genre genre) {
        if (genre == null || genre.getId() == 0) {
            return;
        }
        var existing = (Genre) em.find(Genre.class, genre.getId());

        if (existing == null) {
            return;
        }

        em.remove(em.contains(genre) ? genre : em.merge(genre));
    }
}
