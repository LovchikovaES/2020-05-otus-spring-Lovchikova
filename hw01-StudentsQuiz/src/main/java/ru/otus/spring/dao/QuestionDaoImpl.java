package ru.otus.spring.dao;

import ru.otus.spring.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private List<Question> questions = new ArrayList<>();

    @Override
    public void save(Question question) {
        questions.add(question);
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }

}
