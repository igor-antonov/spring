package ru.otus.service;

import ru.otus.QuestionScanner;
import ru.otus.Student;

public interface StudentService {

    void setAttr(String attr, QuestionScanner questionScanner);
    Student getStudent();
}
