package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
