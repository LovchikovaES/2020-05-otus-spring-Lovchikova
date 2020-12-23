package ru.otus.spring.quiz;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.io.IOService;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.LocalizedMessageService;
import ru.otus.spring.service.QuestionService;

import java.util.List;

@Service("quiz")
public class QuizImpl implements Quiz {
    private final QuestionService questionService;
    private final LocalizedMessageService localizedMessageService;
    private final AppProperties appProperties;
    private final IOService ioService;
    private int quantityCorrectAnswers;
    private String lastName;
    private String firstName;

    public QuizImpl(QuestionService questionService,
                    LocalizedMessageService localizedMessageService,
                    AppProperties appProperties,
                    IOService ioService) {
        this.questionService = questionService;
        this.appProperties = appProperties;
        this.ioService = ioService;
        this.localizedMessageService = localizedMessageService;
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
            ioService.output(question);
            try {
                answerIndex = Integer.parseInt(ioService.get());
                if (question.isAnswerCorrect(answerIndex)) {
                    quantityCorrectAnswers++;
                    ioService.output(localizedMessageService.getMessage("answer.correct"));
                } else {
                    ioService.output(localizedMessageService.getMessage("answer.incorrect"));
                }
            } catch (NumberFormatException e) {
                ioService.output(localizedMessageService.getMessage("answer.incorrect"));
            }
        }
    }

    private void showResults() {
        if (this.quantityCorrectAnswers >= appProperties.getQuantityCorrectAnswersToPass()) {
            ioService.output(this.localizedMessageService.getMessage("quiz.status.success"));
        } else {
            ioService.output(localizedMessageService.getMessage("quiz.status.failed"));
        }
    }

    private void askName() {
        ioService.output(localizedMessageService.getMessage("last.name"));
        this.lastName = ioService.get();
        ioService.output(localizedMessageService.getMessage("first.name"));
        this.firstName = ioService.get();
    }
}
