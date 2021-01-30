package ru.otus.spring.events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.repository"})
@Import(MongoBookCascadeDeleteEventListener.class)
class MongoBookCascadeDeleteEventListenerTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void shouldDeleteCommentsWhenDeletingBook() {
        var bookToDelete = bookRepository.findByName("Island of the Lost");
        assertTrue(bookToDelete.isPresent());

        var bookIdToDelete = bookToDelete.get().getId();
        assertTrue(commentRepository.findAllByBookId(bookIdToDelete).size() > 0);

        bookRepository.delete(bookToDelete.get());
        assertTrue(bookRepository.findById(bookIdToDelete).isEmpty());
        assertEquals(0, commentRepository.findAllByBookId(bookIdToDelete).size());
    }
}