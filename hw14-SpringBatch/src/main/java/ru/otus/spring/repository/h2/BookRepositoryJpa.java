package ru.otus.spring.repository.h2;

import org.springframework.stereotype.Repository;
import ru.otus.spring.model.h2.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Book> findBookById(long id) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b left join fetch b.genre where b.id = :id",
                Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b left join fetch b.genre", Book.class).getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void delete(Book book) {
        if (book == null || book.getId() == 0) {
            return;
        }
        var existing = (Book) em.find(Book.class, book.getId());

        if (existing == null) {
            return;
        }

        em.remove(em.contains(book) ? book : em.merge(book));
    }
}
