package ru.otus.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String name;

    private List<Author> authors = new ArrayList<>();

    private String genre;

    public Book() {
    }

    public Book(String name, String genre, List<Author> authors) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void clearAuthors() {
        authors.clear();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId().equals(book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Book{");
        stringBuilder.append("id=")
                .append(id)
                .append(", name=")
                .append(name)
                .append(", genre=")
                .append(genre)
                .append(", authors={ ");

        for (var author : authors) {
            stringBuilder.append(author.toString())
                    .append(',');
        }
        stringBuilder.append(" } }");

        return stringBuilder.toString();
    }
}
