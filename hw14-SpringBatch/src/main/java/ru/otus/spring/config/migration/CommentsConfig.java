package ru.otus.spring.config.migration;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.model.h2.Comment;
import ru.otus.spring.repository.mongo.BookRepository;

import javax.persistence.EntityManagerFactory;

@Configuration
public class CommentsConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @StepScope
    @Bean
    public JpaPagingItemReader<Comment> commentItemReader() {
        JpaPagingItemReader<Comment> commentItemReader = new JpaPagingItemReader<>();
        commentItemReader.setQueryString("select c from Comment c");
        commentItemReader.setEntityManagerFactory(entityManagerFactory);
        return commentItemReader;
    }

    @StepScope
    @Bean
    public ItemProcessor<Comment, ru.otus.spring.model.mongo.Comment> commentItemProcessor(BookRepository bookRepository) {
        return comment -> {
            ru.otus.spring.model.mongo.Comment commentMongo = new ru.otus.spring.model.mongo.Comment();
            commentMongo.setReview(comment.getReview());
            bookRepository.findByName(comment.getBook().getName()).ifPresent(commentMongo::setBook);
            return commentMongo;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.spring.model.mongo.Comment> commentItemWriter() {
        MongoItemWriter<ru.otus.spring.model.mongo.Comment> itemWriter = new MongoItemWriter<>();
        itemWriter.setTemplate(mongoTemplate);
        itemWriter.setCollection("comments");
        return itemWriter;
    }
}
