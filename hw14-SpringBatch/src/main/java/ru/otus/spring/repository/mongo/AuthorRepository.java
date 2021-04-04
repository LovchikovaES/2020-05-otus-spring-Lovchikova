package ru.otus.spring.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.mongo.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);
}
