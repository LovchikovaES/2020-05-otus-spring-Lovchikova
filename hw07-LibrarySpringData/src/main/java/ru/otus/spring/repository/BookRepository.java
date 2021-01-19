package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(long id);

    @Query("select b from Book b left join fetch b.genre")
    List<Book> findAll();

    Book save(Book book);

    void delete(Book book);
}
