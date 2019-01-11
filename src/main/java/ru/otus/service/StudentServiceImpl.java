package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.QuestionScanner;
import ru.otus.Student;
import java.util.Locale;

@Service
public class StudentServiceImpl implements StudentService {

    private MessageSource ms;

    @Autowired
    private Locale locale;
    private Student student = new Student();

    @Autowired
    public StudentServiceImpl(MessageSource ms){
        this.ms = ms;
    }

    public void setAttr(String attr, QuestionScanner questionScanner) {

        if (attr == ms.getMessage("name.first", null, locale)){
            System.out.println(attr);
            student.setFirst_name(questionScanner.ask(attr));
        }
        else if (attr == ms.getMessage("name.second", null, locale)){
            System.out.println(attr);
            student.setSecond_name(questionScanner.ask(attr));
        }
    }
    public Student getStudent(){
        return student;
    }
}
