package ru.otus.spring.auth;

public class User {
    private final String lastName;
    private final String firstName;

    public User(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
