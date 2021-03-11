package ru.otus.spring.service;

import ru.otus.spring.model.User;

import java.util.Optional;

public interface DbServiceUser {
    Optional<User> getUserByUsername(String username);
}
