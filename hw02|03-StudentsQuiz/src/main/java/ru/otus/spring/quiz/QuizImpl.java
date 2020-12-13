package ru.otus.spring.quiz;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.QuestionService;

import java.util.List;
import java.util.Scanner;

@Service("quiz")
public class QuizImpl implements Quiz {
    private final QuestionService questionService;
    private final MessageSource messageSource;
    private final AppProperties appProperties;
    private int quantityCorrectAnswers;

    public QuizImpl(QuestionService questionService,
                    MessageSource messageSource,
                    AppProperties appProperties) {
        this.questionService = questionService;
        this.appProperties = appProperties;
        this.messageSource = messageSource;
    }

    @Override
    public void start() {
        List<Question> questions = questionService.getAll();
        Scanner in = new Scanner(System.in);
        for (var question : questions) {
            System.out.println(question);
            int answerIndex = in.nextInt();
            if (question.isAnswerCorrect(answerIndex)) {
                quantityCorrectAnswers++;
                System.out.println(messageSource.getMessage("answer.correct", new String[]{}, appProperties.getLocale()));
            } else {
                System.out.println(messageSource.getMessage("answer.incorrect", new String[]{}, appProperties.getLocale()));
            }
        }
        in.close();
        if (quantityCorrectAnswers >= appProperties.getQuantityCorrectAnswersToPass()) {
            System.out.println(messageSource.getMessage("quiz.status.success", new String[]{}, appProperties.getLocale()));
        } else {
            System.out.println(messageSource.getMessage("quiz.status.failed", new String[]{}, appProperties.getLocale()));
        }
    }
}
