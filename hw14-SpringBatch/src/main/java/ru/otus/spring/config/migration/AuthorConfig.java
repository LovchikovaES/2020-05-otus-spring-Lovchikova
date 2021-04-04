package ru.otus.spring.config.migration;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.model.h2.Author;

import javax.persistence.EntityManagerFactory;

@Configuration
public class AuthorConfig {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @StepScope
    @Bean
    public JpaPagingItemReader<Author> authorItemReader() {
        JpaPagingItemReader<Author> authorItemReader = new JpaPagingItemReader<>();
        authorItemReader.setQueryString("select a from Author a");
        authorItemReader.setEntityManagerFactory(entityManagerFactory);
        return authorItemReader;
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, ru.otus.spring.model.mongo.Author> authorItemProcessor() {
        return author -> {
            ru.otus.spring.model.mongo.Author authorMongo = new ru.otus.spring.model.mongo.Author();
            authorMongo.setName(author.getName());
            return authorMongo;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.spring.model.mongo.Author> authorItemWriter() {
        MongoItemWriter<ru.otus.spring.model.mongo.Author> itemWriter = new MongoItemWriter<>();
        itemWriter.setTemplate(mongoTemplate);
        itemWriter.setCollection("authors");
        return itemWriter;
    }
}
