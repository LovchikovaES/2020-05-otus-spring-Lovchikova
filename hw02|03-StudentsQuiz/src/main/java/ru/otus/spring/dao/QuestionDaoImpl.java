package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;
import ru.otus.spring.utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {
    private final String csvPath;

    @Autowired
    public QuestionDaoImpl(MessageSource messageSource, AppProperties appProperties) {
        csvPath = messageSource.getMessage("quiz.csv.path", new String[]{}, appProperties.getLocale());
    }

    public QuestionDaoImpl(String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        var csvLineValues = CsvReader.getCsvLineValues(this.csvPath);
        for (var line : csvLineValues) {
            questions.add(new Question(line.get(0), createAnswers(line.subList(2, line.size()), line.get(1))));
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
