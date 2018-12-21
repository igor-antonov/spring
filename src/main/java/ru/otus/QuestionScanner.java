package ru.otus;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Igor on 18.12.2018.
 */
public class QuestionScanner {
    private final Scanner scanner;
    private final PrintStream out;

    public QuestionScanner(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public String ask(String message) {
        return scanner.nextLine();
    }
}
