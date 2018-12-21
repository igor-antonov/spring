package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/context.xml");
        CSVQuestionsReader reader = context.getBean(CSVQuestionsReader.class);
        reader.setAnswers();
        ExamService examService = context.getBean(ExamService.class);
        System.out.println("Количество правильных ответов: " + examService.checkTest(new QuestionScanner(System.in, System.out)));
    }
}