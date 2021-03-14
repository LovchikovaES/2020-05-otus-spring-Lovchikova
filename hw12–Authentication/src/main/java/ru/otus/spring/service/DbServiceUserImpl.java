package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.User;
import ru.otus.spring.repository.UserRepository;

import java.util.Optional;

@Service
public class DbServiceUserImpl implements DbServiceUser {

    private final UserRepository userRepository;

    public DbServiceUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
