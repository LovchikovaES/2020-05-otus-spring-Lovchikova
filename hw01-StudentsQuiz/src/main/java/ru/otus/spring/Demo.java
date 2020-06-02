package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.data.DataInitializer;
import ru.otus.spring.data.DataInitializerCsv;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.QuestionService;

import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws RuntimeException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        DataInitializer dataInitializer = new DataInitializerCsv(context);
        dataInitializer.createQuestionsAndAnswers();

        startQuiz(context);
    }

    public static void startQuiz(ApplicationContext context) {
        QuestionService questionService = context.getBean(QuestionService.class);
        List<Question> questions = questionService.getAll();

        Scanner in = new Scanner(System.in);
        for (var question : questions) {
            System.out.println(question);
            in.nextInt();
        }
        in.close();
    }
}
