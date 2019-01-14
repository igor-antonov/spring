package ru.otus.service;

import java.util.Map;

public interface ExamService {
    void readQuestions();
    int checkTest();
    Map<String, String> getQuestions();
}
