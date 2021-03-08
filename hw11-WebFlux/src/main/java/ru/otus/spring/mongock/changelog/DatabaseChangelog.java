package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;

import java.util.*;

@ChangeLog
public class DatabaseChangelog {

    private Map<String, Author> authorByNames = new HashMap<>();

    @ChangeSet(order = "001", id = "dropDb", author = "elovchikova", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "elovchikova")
    public void insertAuthors(MongockTemplate mongockTemplate) {
        List<Author> authorList = new ArrayList<>();
        authorList.add(mongockTemplate.save(new Author("Joan Druett")));
        authorList.add(mongockTemplate.save(new Author("Lee Child")));
        authorList.add(mongockTemplate.save(new Author("Andrew Child")));
        authorList.add(mongockTemplate.save(new Author("Antoine de Saint-Exupéry")));
        authorList.add(mongockTemplate.save(new Author("Ray Bradbury")));
        authorList.add(mongockTemplate.save(new Author("Agatha Christie")));
        authorList.add(mongockTemplate.save(new Author("Fletcher Pratt")));
        authorList.add(mongockTemplate.save(new Author("L. Sprague de Camp")));

        for (var author : authorList) {
            authorByNames.put(author.getName(), author);
        }
    }

    @ChangeSet(order = "003", id = "insertBooks", author = "elovchikova")
    public void insertBooks(MongockTemplate mongockTemplate) {
        mongockTemplate.save(new Book("Island of the Lost",
                "adventure",
                Collections.singletonList(authorByNames.get("Joan Druett"))));

        mongockTemplate.save(new Book("In the Wake of Madness",
                "adventure",
                Collections.singletonList(authorByNames.get("Joan Druett"))));

        mongockTemplate.save(new Book("The Sentinel: A Jack Reacher Novel",
                "adventure",
                Arrays.asList(authorByNames.get("Lee Child"), authorByNames.get("Andrew Child"))));

        mongockTemplate.save(new Book("The Little Prince",
                "classics",
                Collections.singletonList(authorByNames.get("Antoine de Saint-Exupéry"))));

        mongockTemplate.save(new Book("Fahrenheit 451",
                "classics",
                Collections.singletonList(authorByNames.get("Ray Bradbury"))));

        mongockTemplate.save(new Book("Dandelion Wine",
                "classics",
                Collections.singletonList(authorByNames.get("Ray Bradbury"))));

        mongockTemplate.save(new Book("The Man in the Brown Suit",
                "detective",
                Collections.singletonList(authorByNames.get("Agatha Christie"))));

        mongockTemplate.save(new Book("The Complete Compleat Enchanter",
                "fantasy",
                Arrays.asList(authorByNames.get("Fletcher Pratt"), authorByNames.get("L. Sprague de Camp"))));
    }
}
