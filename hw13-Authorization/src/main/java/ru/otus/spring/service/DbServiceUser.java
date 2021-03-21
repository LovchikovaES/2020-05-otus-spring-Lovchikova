package ru.otus.spring.service;

import ru.otus.spring.dto.UserDto;

import java.util.Optional;

public interface DbServiceUser {
    Optional<UserDto> getUserByUsername(String username);
}
