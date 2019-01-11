package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.Main;
import ru.otus.Student;
import ru.otus.service.ExamService;
import ru.otus.QuestionScanner;
import ru.otus.service.ExamServiceCSV;
import ru.otus.service.StudentService;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Igor on 18.12.2018.
 */

public class SpringTest {

    private static ExamService examService;
    private static StudentService studentService;
    private static MessageSource ms;
    private static Locale locale;
    private static Student student;
    private static QuestionScanner scannerMock;

    @BeforeAll
    public static void prepareTest(){

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        locale = context.getBean(Locale.class);
        ms = context.getBean(MessageSource.class);
        examService = context.getBean(ExamService.class);
        examService.readQuestions();
        studentService = context.getBean(StudentService.class);
        scannerMock = mock(QuestionScanner.class);
    }

    @Test
    @DisplayName("Quantity of Questions")
    public void getQuantityQuestions(){
        int quantityQuestions = examService.getQuestions().size();
        Assertions.assertEquals(5, quantityQuestions);
    }

    @Test
    @DisplayName("Test passed Default Locale(RU)")
    public void getPassExam(){
        when(scannerMock.ask("Третья планета от солнца")).thenReturn("Земля");
        when(scannerMock.ask("Год окончания второй мировой войны")).thenReturn("1945");
        when(scannerMock.ask("Столица России")).thenReturn("Москва");
        when(scannerMock.ask("Сколько часов в сутках")).thenReturn("24");
        when(scannerMock.ask("Основатель Facebook (Фамилия)")).thenReturn("Цукерберг");
        Assertions.assertEquals(5, examService.checkTest(scannerMock));

    }

    @Test
    @DisplayName("Test failed Default Locale(RU)")
    public void getFailExam(){
        when(scannerMock.ask("Третья планета от солнца")).thenReturn("Марс");
        when(scannerMock.ask("Год окончания второй мировой войны")).thenReturn("1941");
        when(scannerMock.ask("Столица России")).thenReturn("Сочи");
        when(scannerMock.ask("Сколько часов в сутках")).thenReturn("30");
        when(scannerMock.ask("Основатель Facebook (Фамилия)")).thenReturn("Дуров");
        Assertions.assertNotEquals(5, examService.checkTest(scannerMock));
    }

    @Test
    @DisplayName("Test first name")
    public void getStudentName(){
        String firstName = ms.getMessage("name.first", null, locale);
        when(scannerMock.ask(firstName)).thenReturn("Иван");
        studentService.setAttr(firstName, scannerMock);

        student = studentService.getStudent();
        Assertions.assertEquals("Иван", student.getFirst_name());
    }

    @Test
    @DisplayName("Test second name")
    public void getStudentSecondName(){
        String secondName = ms.getMessage("name.second", null, locale);
        when(scannerMock.ask(secondName)).thenReturn("Иванов");
        studentService.setAttr(secondName, scannerMock);
        student = studentService.getStudent();
        Assertions.assertEquals("Иванов", student.getSecond_name());
    }

    @Test
    @DisplayName("Test english questions")
    public void getEnglishLocalQuestions(){
        examService = new ExamServiceCSV(ms.getMessage("csv.path",null, Locale.ENGLISH));
        examService.readQuestions();
        when(scannerMock.ask("Third planet from Sun")).thenReturn("Earth");
        when(scannerMock.ask("Year of the end of world war ii")).thenReturn("1945");
        when(scannerMock.ask("Capital of Russia")).thenReturn("Moscow");
        when(scannerMock.ask("How many hours in days")).thenReturn("24");
        when(scannerMock.ask("Founder of Facebook (second name)")).thenReturn("Zuckerberg");
        Assertions.assertEquals(5, examService.checkTest(scannerMock));
    }
}
