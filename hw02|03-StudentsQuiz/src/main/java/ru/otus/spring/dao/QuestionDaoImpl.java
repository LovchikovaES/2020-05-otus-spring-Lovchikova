package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.csv.CsvReader;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;

import java.util.ArrayList;
import java.util.List;

@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {
    private final CsvReader csvReader;

    public QuestionDaoImpl(AppProperties appProperties) {
        this.csvReader = new CsvReader(String.format(appProperties.getCsvPath(), appProperties.getLocale()));
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        var csvRows = csvReader.getCsvLineValues();
        try {
            for (var row : csvRows) {
                var questionText = row.get(0);
                var correctAnswer = row.get(1);
                var answersTexts = row.subList(2, row.size());
                int i = 0;
                for (var answersText : answersTexts) {
                    if (answersText.isEmpty()) {
                        answersTexts.remove(i);
                    }
                    i++;
                }
                if (questionText.isEmpty() || correctAnswer.isEmpty() || answersTexts.size() == 0) {
                    throw new RuntimeException("Incorrect structure of csv file");
                }
                questions.add(new Question(questionText, createAnswers(answersTexts, correctAnswer)));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Incorrect structure of csv file");
        }
        return questions;
    }

    private List<Answer> createAnswers(List<String> answers, String correctAnswer) {
        List<Answer> answerList = new ArrayList<>();
        for (var answer : answers) {
            boolean isCorrect = false;
            if (answer.equals(correctAnswer)) {
                isCorrect = true;
            }
            answerList.add(new Answer(answer, isCorrect));
        }
        return answerList;
    }

}
