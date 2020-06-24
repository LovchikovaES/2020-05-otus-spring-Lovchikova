package ru.otus.spring.data;

public class DataInitializerException extends RuntimeException {
    public DataInitializerException(Exception e) {
        super(e);
    }
}
