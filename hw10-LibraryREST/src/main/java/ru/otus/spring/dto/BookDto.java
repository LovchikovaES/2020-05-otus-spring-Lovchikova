package ru.otus.spring.dto;

import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;

import java.util.List;

public class BookDto {
    private Long id;
    private String name;
    private List<Author> authors;
    private Genre genre;

    public BookDto(Long id, String name, List<Author> authors, Genre genre) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.genre = genre;
    }

    public BookDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Book{");
        description.append("id=")
                .append(this.id)
                .append(", name=")
                .append(this.name);
        if (this.genre != null) {
            description.append(", genre=")
                    .append(this.genre);
        }
        if (this.authors != null) {
            description.append(", authors: {");
            for (var author : this.authors) {
                description.append(author);
                description.append("; ");
            }
            description.append("}}");
        }
        return description.toString();
    }
}
