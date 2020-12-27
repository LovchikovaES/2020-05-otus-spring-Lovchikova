package ru.otus.spring.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.quiz.Quiz;

@ShellComponent
public class ApplicationEventsCommands {

    private final Quiz quiz;

    public ApplicationEventsCommands(Quiz quiz) {
        this.quiz = quiz;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login() {
        quiz.login();
    }

    @ShellMethod(value = "Run quiz command", key = {"q", "quiz"})
    @ShellMethodAvailability(value = "isQuizCommandAvailable")
    public void quiz() {
        quiz.start();
    }

    private Availability isQuizCommandAvailable() {
        return quiz.getFirstName() == null || quiz.getLastName() == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }
}
