package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.*;

@ComponentScan
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);


        ExamService examService = context.getBean(ExamService.class);
        examService.readQuestions();
        StudentService studentService = context.getBean(StudentService.class);
        LocalizedService messageService = context.getBean(LocalizedService.class);

        studentService.askStudentFirstName();
        studentService.askStudentSecondName();

        Student student = studentService.getStudent();
        String result = messageService.getMessage("result");
        String studentTag = messageService.getMessage("student.tag");

        System.out.println(result + " " + examService.checkTest());
        System.out.println(studentTag + " " + student.getFirstName() + " " + student.getSecondName());

    }
}