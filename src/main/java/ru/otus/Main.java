package ru.otus;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.ExamService;
import ru.otus.service.StudentService;
import java.util.Locale;

@ComponentScan
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        Locale locale = context.getBean(Locale.class);
        MessageSource ms = context.getBean(MessageSource.class);
        ExamService examService = context.getBean(ExamService.class);
        examService.readQuestions();
        StudentService studentService = context.getBean(StudentService.class);
        QuestionScanner questionScanner = context.getBean(QuestionScanner.class);

        studentService.setAttr(ms.getMessage("name.first", null, locale),
                questionScanner);
        studentService.setAttr(ms.getMessage("name.second", null, locale),
                questionScanner);

        Student student = studentService.getStudent();
        String result = ms.getMessage("result", null, locale);
        String studentTag =ms.getMessage("student.tag", null, locale);

        System.out.println(result + " " + examService.checkTest(new QuestionScanner(System.in)));
        System.out.println(studentTag + " " + student.getFirst_name() + " " + student.getSecond_name());

    }
}