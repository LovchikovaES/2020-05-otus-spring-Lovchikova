package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.config.AppProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QuestionDaoImplTest {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private QuestionDao questionDao;

    @Test
    void checkQuestionQuantities() {
        assertEquals(3, questionDao.getAll().size());
    }

    @Test
    void checkExistAnswersForQuestions() {
        assertThat(questionDao.getAll()).allMatch(q -> q.getAnswers() != null && q.getAnswers().size() > 0);
    }

    @Test
    void checkCorrectAnswerExistsForQuestions() {
        var questions = questionDao.getAll();
        boolean hasCorrectAnswer = false;

        for (var question : questions) {
            var answers = question.getAnswers();
            for (var answer : answers) {
                if (answer.isCorrect()) {
                    hasCorrectAnswer = true;
                    break;
                }
            }
            if (!hasCorrectAnswer) {
                break;
            }
        }
        assertTrue(hasCorrectAnswer);
    }
}