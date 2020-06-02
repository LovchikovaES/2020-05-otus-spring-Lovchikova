package ru.otus.spring.data;

public class ResourceQuiz implements ResourceFile {
    final String path;

    public ResourceQuiz(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
