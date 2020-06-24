package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.model.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void add(Question question) {
        questionDao.save(question);
    }

    @Override
    public List<Question> getAll() {
        return questionDao.getAll();
    }
}
