package ru.otus.spring.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_comments")
public class Comment {
    @Id
    private String id;

    private String review;

    @DBRef
    private Book book;

    public Comment() {
    }

    public Comment(String review, Book book) {
        this.review = review;
        this.book = book;
    }

    public Comment(String comment) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", review=" + review;
    }
}
