package ru.otus.spring.data;

public interface DataInitializer {
    void createQuestionsAndAnswers() throws DataInitializerException;
}
