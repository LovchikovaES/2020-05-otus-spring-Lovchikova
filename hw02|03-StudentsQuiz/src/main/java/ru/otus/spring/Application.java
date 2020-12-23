package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.quiz.Quiz;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application {
    public static void main(String[] args) throws RuntimeException {
        var context = SpringApplication.run(Application.class, args);
        Quiz quiz = context.getBean(Quiz.class);
        quiz.start();
    }
}
