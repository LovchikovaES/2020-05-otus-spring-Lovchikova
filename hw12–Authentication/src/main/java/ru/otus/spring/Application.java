package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);

        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        var pass = passwordEncoder.encode("admin");
    }
}

