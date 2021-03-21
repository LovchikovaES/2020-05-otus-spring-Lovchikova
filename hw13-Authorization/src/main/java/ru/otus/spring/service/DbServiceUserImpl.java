package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.UserDto;
import ru.otus.spring.model.user.Privilege;
import ru.otus.spring.model.user.Role;
import ru.otus.spring.model.user.User;
import ru.otus.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbServiceUserImpl implements DbServiceUser {

    private final UserRepository userRepository;

    public DbServiceUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convertUserToDto(user.get()));
    }

    private UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEnabled(user.isEnabled());
        List<Role> roles = new ArrayList<>(user.getRoles());
        for (var role : roles) {
            List<Privilege> privileges = new ArrayList<>(role.getPrivileges());
        }
        userDto.setRoles(roles);
        return userDto;
    }
}
