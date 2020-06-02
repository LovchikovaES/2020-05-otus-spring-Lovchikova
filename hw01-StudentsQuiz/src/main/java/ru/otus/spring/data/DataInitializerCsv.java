package ru.otus.spring.data;

import org.springframework.context.ApplicationContext;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataInitializerCsv implements DataInitializer {

    public static final String CSV_DELIMITER = ";";
    private final ApplicationContext context;

    public DataInitializerCsv(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void createQuestionsAndAnswers() {
        QuestionService questionService = context.getBean(QuestionService.class);
        try {
            var csvLineValues = getCsvLineValues();
            for (var line : csvLineValues) {
                questionService.add(new Question(line.get(0), createAnswers(line.subList(2, line.size()), line.get(1))));
            }
        } catch (Exception e) {
            throw new DataInitializerException(e);
        }
    }

    private List<List<String>> getCsvLineValues() {
        List<List<String>> lineValues = new ArrayList<>();

        ResourceFile quizResourceFile = context.getBean(ResourceQuiz.class);

        try (Scanner lineScanner = new Scanner(getClass().getResourceAsStream("/" + quizResourceFile.getPath()))) {
            while (lineScanner.hasNextLine()) {
                List<String> values = new ArrayList<>();
                try (Scanner rowScanner = new Scanner(lineScanner.nextLine())) {
                    rowScanner.useDelimiter(CSV_DELIMITER);
                    while (rowScanner.hasNext()) {
                        values.add(rowScanner.next());
                    }
                }
                lineValues.add(values);
            }
        }
        return lineValues;
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
