package ru.otus.spring.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOConsoleService implements IOService {
    private final PrintStream out;
    private final Scanner in;

    public IOConsoleService(PrintStream out, InputStream in) {
        this.out = out;
        this.in = new Scanner(in);
    }

    @Autowired
    public IOConsoleService() {
        this.out = System.out;
        this.in = new Scanner(System.in);
    }

    @Override
    public void put(Object object) {
        out.println(object.toString());
    }

    @Override
    public String get() {
        return in.next();
    }
}
