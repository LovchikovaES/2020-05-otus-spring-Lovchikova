package ru.otus.spring.dao;

import ru.otus.spring.model.Question;

import java.util.List;

public interface QuestionDao {
    void save(Question question);

    List<Question> getAll();
}
