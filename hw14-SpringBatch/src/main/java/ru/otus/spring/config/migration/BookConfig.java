package ru.otus.spring.config.migration;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.model.h2.Book;
import ru.otus.spring.model.mongo.Author;
import ru.otus.spring.repository.mongo.AuthorRepository;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BookConfig {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @StepScope
    @Bean
    public JpaPagingItemReader<Book> bookItemReader() {
        JpaPagingItemReader<Book> bookItemReader = new JpaPagingItemReader<>();
        bookItemReader.setQueryString("select b from Book b left join fetch b.genre");
        bookItemReader.setEntityManagerFactory(entityManagerFactory);
        return bookItemReader;
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, ru.otus.spring.model.mongo.Book> bookItemProcessor(AuthorRepository authorRepository) {
        return book -> {
            ru.otus.spring.model.mongo.Book bookMongo = new ru.otus.spring.model.mongo.Book();
            bookMongo.setName(book.getName());
            bookMongo.setGenre(book.getGenre().getName());

            List<Author> authors = new ArrayList<>();
            for (var authorH2 : book.getAuthors()) {
                authorRepository.findByName(authorH2.getName()).ifPresent(authors::add);
            }
            bookMongo.setAuthors(authors);
            return bookMongo;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.spring.model.mongo.Book> bookItemWriter() {
        MongoItemWriter<ru.otus.spring.model.mongo.Book> itemWriter = new MongoItemWriter<>();
        itemWriter.setTemplate(mongoTemplate);
        itemWriter.setCollection("books");
        return itemWriter;
    }
}
