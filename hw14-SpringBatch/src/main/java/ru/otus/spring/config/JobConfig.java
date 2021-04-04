package ru.otus.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.model.h2.Author;
import ru.otus.spring.model.h2.Book;
import ru.otus.spring.model.h2.Comment;


@Configuration
public class JobConfig {
    public static final String MIGRATE_TABLES = "migrateTables";
    private static final int CHUNK_SIZE = 5;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job migrateTables(Step migrateAuthors, Step migrateBooks, Step migrateComments) {
        return jobBuilderFactory.get(MIGRATE_TABLES)
                .start(migrateAuthors)
                .next(migrateBooks)
                .next(migrateComments)
                .build();
    }

    @Bean
    public Step migrateAuthors(JpaPagingItemReader<Author> reader,
                               MongoItemWriter<ru.otus.spring.model.mongo.Author> writer,
                               ItemProcessor<Author, ru.otus.spring.model.mongo.Author> processor) {
        return stepBuilderFactory.get("migrateAuthors")
                .<Author, ru.otus.spring.model.mongo.Author>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step migrateBooks(JpaPagingItemReader<Book> reader,
                             MongoItemWriter<ru.otus.spring.model.mongo.Book> writer,
                             ItemProcessor<Book, ru.otus.spring.model.mongo.Book> processor) {
        return stepBuilderFactory.get("migrateBooks")
                .<Book, ru.otus.spring.model.mongo.Book>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step migrateComments(JpaPagingItemReader<Comment> reader,
                                MongoItemWriter<ru.otus.spring.model.mongo.Comment> writer,
                                ItemProcessor<Comment, ru.otus.spring.model.mongo.Comment> processor) {
        return stepBuilderFactory.get("migrateComments")
                .<Comment, ru.otus.spring.model.mongo.Comment>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
