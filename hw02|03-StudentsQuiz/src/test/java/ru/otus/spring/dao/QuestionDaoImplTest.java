package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.config.AppProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoImplTest {

    private AppProperties appProperties = new AppProperties();
    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        appProperties.setLocale(Locale.UK);
        appProperties.setCsvPath("/quiz/quiz_%s.csv");
        this.questionDao = new QuestionDaoImpl(appProperties);
    }

    @Test
    void checkQuestionQuantities() {
        assertEquals(3, questionDao.getAll().size());
    }

    @Test
    void checkExistAnswersForQuestions() {
        var questions = questionDao.getAll();
        boolean hasAnswers = false;

        for (var question : questions) {
            if (question.getAnswers().size() > 0) {
                hasAnswers = true;
            }
            if (!hasAnswers) {
                break;
            }
        }
        assertTrue(hasAnswers);
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

    @Test
    void checkIncorrectCsvStructure() {
        AppProperties appProps = new AppProperties();
        appProps.setLocale(Locale.US);
        appProps.setCsvPath("/quiz/quiz_%s.csv");
        QuestionDao questionDao = new QuestionDaoImpl(appProps);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            questionDao.getAll();
        });
        assertTrue(exception.getMessage().contains("Incorrect structure of csv file"));
    }


}