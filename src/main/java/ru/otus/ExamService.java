package ru.otus;

import java.util.Map;

/**
 * Created by Igor on 18.12.2018.
 */
public class ExamService {

    public ExamService(CSVQuestionsReader questionsReader) {
        this.questions = questionsReader.getAnswers();
    }

    private Map<String, String> questions;
    private Integer correctAnswerCount = 0;

    public int checkTest(QuestionScanner questionScanner) {
        for (Map.Entry<String, String> question : questions.entrySet()) {
            System.out.println(question.getKey());
            String answer = questionScanner.ask(question.getKey());
            if (question.getValue().equals(answer)) {
                correctAnswerCount++;
            }
        }
        return correctAnswerCount;
    }
}
