package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.csv.CsvReader;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoImplTest {

    private AppProperties appProperties = new AppProperties();
    private QuestionDao questionDao;
    private CsvReader csvReader = new CsvReader();

    @BeforeEach
    void setUp() {
        appProperties.setLocale(Locale.UK);
        appProperties.setCsvPath("/quiz/quiz_%s.csv");
        this.questionDao = new QuestionDaoImpl(appProperties, csvReader);
    }

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

    @Test
    void checkIncorrectCsvStructure() {
        AppProperties appProps = new AppProperties();
        appProps.setLocale(Locale.US);
        appProps.setCsvPath("/quiz/quiz_%s.csv");
        QuestionDao questionDao = new QuestionDaoImpl(appProps, csvReader);
        Exception exception = assertThrows(QuestionDaoException.class, questionDao::getAll);
        assertTrue(exception.getMessage().contains("Incorrect structure of csv file"));
    }


}