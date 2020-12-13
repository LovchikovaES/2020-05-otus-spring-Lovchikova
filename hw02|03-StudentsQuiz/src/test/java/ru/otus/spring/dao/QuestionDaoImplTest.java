package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.config.AppProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {

    @Mock
    MessageSource messageSource;

    @Mock
    AppProperties appProperties;

    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        appProperties.setLocale(Locale.UK);
        this.questionDao = new QuestionDaoImpl("quiz.csv");
    }

    @Test
    void checkQuestionQuantities() {
        assertEquals(3, questionDao.getAll().size());
    }
}