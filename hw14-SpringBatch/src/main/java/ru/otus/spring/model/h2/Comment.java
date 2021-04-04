package ru.otus.spring.model.h2;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private long id;

    @Column(name = "review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment() {
    }

    public Comment(String review) {
        this.review = review;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
