package ru.otus;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Igor on 18.12.2018.
 */
public class QuestionScanner{
    private final Scanner scanner;

    public QuestionScanner(InputStream in) {
        scanner = new Scanner(in);
    }

    public String ask(String message) {
        return scanner.nextLine();
    }
}
