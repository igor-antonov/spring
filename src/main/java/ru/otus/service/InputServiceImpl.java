package ru.otus.service;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Igor on 18.12.2018.
 */


public class InputServiceImpl implements InputService{
    private final Scanner scanner;

    public InputServiceImpl(InputStream in) {
        scanner = new Scanner(in);
    }

    public String ask(String message) {
        return scanner.nextLine();
    }
}
