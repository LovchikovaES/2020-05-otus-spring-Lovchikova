package ru.otus.spring.quiz;

import org.springframework.stereotype.Service;
import ru.otus.spring.auth.User;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.IOMessageService;
import ru.otus.spring.service.QuestionService;

import java.util.List;

@Service("quiz")
public class QuizImpl implements Quiz {
    private final QuestionService questionService;
    private final AppProperties appProperties;
    private final IOMessageService ioMessageService;

    private int quantityCorrectAnswers;
    private User user;

    public QuizImpl(QuestionService questionService,
                    AppProperties appProperties,
                    IOMessageService ioMessageService) {
        this.questionService = questionService;
        this.appProperties = appProperties;
        this.ioMessageService = ioMessageService;
    }

    @Override
    public void start() {
        askQuestions();
        showResults();
    }

    @Override
    public void login() {
        ioMessageService.outputMessage("last.name");
        var lastName = ioMessageService.get();
        ioMessageService.outputMessage("first.name");
        var firstName = ioMessageService.get();
        this.user = new User(lastName, firstName);
    }

    @Override
    public boolean isUserLoggedIn() {
        return user != null;
    }

    private void askQuestions() {
        int answerIndex;
        List<Question> questions = questionService.getAll();
        for (var question : questions) {
            ioMessageService.output(question);
            try {
                answerIndex = Integer.parseInt(ioMessageService.get());
                if (question.isAnswerCorrect(answerIndex)) {
                    quantityCorrectAnswers++;
                    ioMessageService.outputMessage("answer.correct");
                } else {
                    ioMessageService.outputMessage("answer.incorrect");
                }
            } catch (NumberFormatException e) {
                ioMessageService.outputMessage("answer.incorrect");
            }
        }
    }

    private void showResults() {
        if (this.quantityCorrectAnswers >= appProperties.getQuantityCorrectAnswersToPass()) {
            ioMessageService.outputMessage("quiz.status.success");
        } else {
            ioMessageService.outputMessage("quiz.status.failed");
        }
    }
}
