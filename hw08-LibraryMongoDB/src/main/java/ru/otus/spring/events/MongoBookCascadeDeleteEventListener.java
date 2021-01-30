package ru.otus.spring.events;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.CommentRepository;

@Component
public class MongoBookCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    public MongoBookCascadeDeleteEventListener(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        var source = event.getSource();
        var id = source.get("_id").toString();
        for (var comment : commentRepository.findAllByBookId(id)) {
            commentRepository.delete(comment);
        }
    }
}
