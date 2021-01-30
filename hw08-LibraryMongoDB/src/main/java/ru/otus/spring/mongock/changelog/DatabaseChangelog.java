package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ChangeLog
public class DatabaseChangelog {

    private Map<String, Author> authorByNames = new HashMap<>();
    private Map<String, Book> bookByNames = new HashMap<>();

    @ChangeSet(order = "001", id = "dropDb", author = "elovchikova", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "elovchikova")
    public void insertAuthors(AuthorRepository authorRepository) {
        authorRepository.save(new Author("Joan Druett"));
        authorRepository.save(new Author("Lee Child"));
        authorRepository.save(new Author("Andrew Child"));
        authorRepository.save(new Author("Antoine de Saint-Exupéry"));
        authorRepository.save(new Author("Ray Bradbury"));
        authorRepository.save(new Author("Agatha Christie"));
        authorRepository.save(new Author("Fletcher Pratt"));
        authorRepository.save(new Author("L. Sprague de Camp"));

        for (var author : authorRepository.findAll()) {
            authorByNames.put(author.getName(), author);
        }
    }

    @ChangeSet(order = "003", id = "insertBooks", author = "elovchikova")
    public void insertBooks(BookRepository bookRepository) {
        bookRepository.save(new Book("Island of the Lost",
                "adventure",
                Collections.singletonList(authorByNames.get("Joan Druett"))));

        bookRepository.save(new Book("In the Wake of Madness",
                "adventure",
                Collections.singletonList(authorByNames.get("Joan Druett"))));

        bookRepository.save(new Book("The Sentinel: A Jack Reacher Novel",
                "adventure",
                Arrays.asList(authorByNames.get("Lee Child"), authorByNames.get("Andrew Child"))));

        bookRepository.save(new Book("The Little Prince",
                "classics",
                Collections.singletonList(authorByNames.get("Antoine de Saint-Exupéry"))));

        bookRepository.save(new Book("Fahrenheit 451",
                "classics",
                Collections.singletonList(authorByNames.get("Ray Bradbury"))));

        bookRepository.save(new Book("Dandelion Wine",
                "classics",
                Collections.singletonList(authorByNames.get("Ray Bradbury"))));

        bookRepository.save(new Book("The Man in the Brown Suit",
                "detective",
                Collections.singletonList(authorByNames.get("Agatha Christie"))));

        bookRepository.save(new Book("The Complete Compleat Enchanter",
                "fantasy",
                Arrays.asList(authorByNames.get("Fletcher Pratt"), authorByNames.get("L. Sprague de Camp"))));

        for (var book : bookRepository.findAll()) {
            bookByNames.put(book.getName(), book);
        }
    }

    @ChangeSet(order = "004", id = "insertComments", author = "elovchikova")
    public void insertComments(CommentRepository commentRepository) {
        commentRepository.save(new Comment(
                "I am an engineer and in a position to appreciate how hard the tasks accomplished by these shipwrecked men were. Especially impressive was Raynal, the Frenchman from the Grafton. I am not sure I could even duplicate his bird cage, much less his concrete chimney or his handmade nails or his new boat made out of the old shipwreck. I read in the epilogue that original account published of the Grafton shipwreck by the survivors ignited a craze at the time to steer away from technology and get back to first principles like gardening, and shipmaking. I feel the same way today.",
                bookByNames.get("Island of the Lost")));

        commentRepository.save(new Comment(
                "Joan Druett’s wonderful account of two ships which wrecked on New Zealand’s sub-Antarctic Auckland Islands is masterfully done. Never just a dry account of the tasks required for survival, she paints portraits of the two crews with all their strengths and shortcomings. Although the two ships smashed into the forbidding rocky coasts within a short period of each other, neither knew the other was there. Captain Musgrave exhibited good leadership and, that along with the skill and ingenuity of the Frenchman Raynal, resulted in all five men to survive. Misfortune struck the Scottish ship, being burdened with a weak captain and a crew unwilling to do what was required to live.",
                bookByNames.get("Island of the Lost")));

        commentRepository.save(new Comment(
                "Wow..fantastic and incredibly well researched book. Couldnt put this down and finished it in a day! This book as incredibly well written. So often books like these can quickly become dry and you loose interest. Joan was able to use an amazing amount of research and backstory to build up an engaging authentic masterpiece. She was also able to weave together multiple plots quite successfully, and as reader, I wasnt confused. Another reader pointed out similarities in this tale and Ernest Shackletons adventure, which I would definitely agree with.",
                bookByNames.get("Island of the Lost")));

        commentRepository.save(new Comment(
                "This book is a neat read since the author sets the stage from a historical and economical perspective to help the reader understand the rough nature of whaling and being a seaman who could be out at sea for years in some cases. Book is very well researched and you can feel like you are really there oh so many years ago and even sailing out to sea.",
                bookByNames.get("In the Wake of Madness")));

        commentRepository.save(new Comment(
                "This was a very well written book that have the reader a glimpse of life aboard a whaling ship in the mid 1800s. Also the tale of a murder committed on the ship and the retribution meted out because of the crime.",
                bookByNames.get("In the Wake of Madness")));

        commentRepository.save(new Comment(
                "Whenever one of my favorite authors adds a co-writer I usually try them out for a couple of books. Most of the time it doesnt work but there have been a few where it has. This collaboration is not one of those that works. The Reacher in this book is simply not the Jack Reacher we all know and love to read about. This guy does not make me care what happens to him. He has mounted up and jumped far, far over the shark. This might be the last collaboration Jack Reacher for me. Id have to be pretty bored to read the next one. I finished this book but only because I paid for it.",
                bookByNames.get("The Sentinel: A Jack Reacher Novel")));

        commentRepository.save(new Comment(
                "Finally I read THE LITTLE PRINCE. Such a cute book with cute-cute illustrations in it. I had my eye on this book for quite a time. I finally picked this book in the morning and read it. This book is not only for children but for grown-ups too.",
                bookByNames.get("The Little Prince")));

        commentRepository.save(new Comment(
                "Us humans could learn a thing or two about this book, made me realise how materialistic we really are and how we are constantly making the same mistakes. If you can get this book cheap, buy it, read it, let your kids read and who knows we all might be better people going forward. Now that is worth a couple of quid of anyones money!",
                bookByNames.get("The Little Prince")));

        commentRepository.save(new Comment(
                "This is a story that never loses its appeal. I am a child again when I read the words within these pages, celebrating summers that were born 40 years before me. Yet I feel drawn in as if I am the hero, his feelings mirror my own. Ray Bradbury was a genius, evoking feelings I have buried for decades. This story is simple, and timeless. If you havent read this for 30 years, its time to revisit hometown summer vacation memories.",
                bookByNames.get("Dandelion Wine")));

        commentRepository.save(new Comment(
                "good story",
                bookByNames.get("The Complete Compleat Enchanter")));

        commentRepository.save(new Comment(
                "This is classic sci-fi by a master",
                bookByNames.get("The Complete Compleat Enchanter")));

        commentRepository.save(new Comment(
                "Great book. Timeless stories",
                bookByNames.get("The Complete Compleat Enchanter")));
    }

}
