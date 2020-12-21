package ru.otus.spring.quiz;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.config.LocaleMessage;
import ru.otus.spring.io.IOService;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.QuestionService;

import java.util.List;

@Service("quiz")
public class QuizImpl implements Quiz {
    private final QuestionService questionService;
    private final LocaleMessage localeMessage;
    private final AppProperties appProperties;
    private final IOService ioService;
    private int quantityCorrectAnswers;
    private String lastName;
    private String firstName;

    public QuizImpl(QuestionService questionService,
                    LocaleMessage localeMessage,
                    AppProperties appProperties,
                    IOService ioService) {
        this.questionService = questionService;
        this.localeMessage = localeMessage;
        this.appProperties = appProperties;
        this.ioService = ioService;
    }

    @Override
    public void start() {
        askName();
        askQuestions();
        showResults();
    }

    private void askQuestions() {
        int answerIndex;
        List<Question> questions = questionService.getAll();
        for (var question : questions) {
            ioService.put(question);
            try {
                answerIndex = Integer.parseInt(ioService.get());
                if (question.isAnswerCorrect(answerIndex)) {
                    quantityCorrectAnswers++;
                    ioService.put(localeMessage.getMessage("answer.correct"));
                } else {
                    ioService.put(localeMessage.getMessage("answer.incorrect"));
                }
            } catch (NumberFormatException e) {
                ioService.put(localeMessage.getMessage("answer.incorrect"));
            }
        }
    }

    private void showResults() {
        if (this.quantityCorrectAnswers >= appProperties.getQuantityCorrectAnswersToPass()) {
            ioService.put(this.localeMessage.getMessage("quiz.status.success"));
        } else {
            ioService.put(localeMessage.getMessage("quiz.status.failed"));
        }
    }

    private void askName() {
        ioService.put(localeMessage.getMessage("last.name") + ":");
        this.lastName = ioService.get();
        ioService.put(localeMessage.getMessage("first.name") + ":");
        this.firstName = ioService.get();
    }
}
