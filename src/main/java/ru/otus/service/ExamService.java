package ru.otus.service;

import ru.otus.QuestionScanner;

import java.util.Map;

public interface ExamService {
    void readQuestions();
    int checkTest(QuestionScanner questionScanner);
    Map<String, String> getQuestions();
}
