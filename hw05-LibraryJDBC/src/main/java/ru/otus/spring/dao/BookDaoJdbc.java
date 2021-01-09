package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       AuthorDao authorDao,
                       GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", book.getName());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "insert into books (`name`, `author_id`, `genre_id`) values (:name, :author_id, :genre_id)", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from books where id = :id", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        Map<Long, Author> authors =
                authorDao.getAll().stream().collect(Collectors.toMap(Author::getId, author -> author));
        Map<Long, Genre> genres =
                genreDao.getAll().stream().collect(Collectors.toMap(Genre::getId, genre -> genre));
        return namedParameterJdbcOperations.query("select * from books", new BookMapper(authors, genres));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params);
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id ", params);
    }

    private class BookMapper implements RowMapper<Book> {

        private Map<Long, Author> allAuthors = new HashMap<>();
        private Map<Long, Genre> allGenres = new HashMap<>();

        private BookMapper(Map<Long, Author> authors,
                           Map<Long, Genre> genres) {
            this.allGenres = genres;
            this.allAuthors = authors;
        }

        private BookMapper() {
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            Author author = allAuthors.get(authorId) == null ? authorDao.getById(authorId) : allAuthors.get(authorId);
            Genre genre = allGenres.get(genreId) == null ? genreDao.getById(genreId) : allGenres.get(genreId);
            return new Book(id, name, author, genre);
        }
    }
}
