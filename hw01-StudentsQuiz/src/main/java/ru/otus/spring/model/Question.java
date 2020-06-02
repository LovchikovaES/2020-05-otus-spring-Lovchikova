package ru.otus.spring.model;

import java.util.List;

public class Question {
    private final String question;
    private final List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder(getQuestion() + '\n');
        for (var i = 0; i < answers.size(); i++) {
            outputString.append(i + 1)
                        .append(") ")
                        .append(answers.get(i));
            if (i + 1 < answers.size()) {
                outputString.append('\n');
            }
        }
        return outputString.toString();
    }
}
