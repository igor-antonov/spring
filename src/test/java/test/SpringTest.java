package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.Main;
import ru.otus.Student;
import ru.otus.service.*;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Igor on 18.12.2018.
 */

public class SpringTest {

    private static ExamService examService;
    private static LocalizedService localizedService;
    private static StudentService studentService;
    private static MessageSource ms;
    private static Student student;
    private static InputServiceImpl inputServiceMock;

    @BeforeAll
    public static void prepareTest(){

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        ms = context.getBean(MessageSource.class);
        localizedService = context.getBean(LocalizedService.class);
        inputServiceMock = mock(InputServiceImpl.class);
        examService = new ExamServiceCSV(localizedService.getCSVPath(), inputServiceMock);
        examService.readQuestions();
        studentService = new StudentServiceImpl(localizedService, inputServiceMock);
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
        when(inputServiceMock.ask("Третья планета от солнца")).thenReturn("Земля");
        when(inputServiceMock.ask("Год окончания второй мировой войны")).thenReturn("1945");
        when(inputServiceMock.ask("Столица России")).thenReturn("Москва");
        when(inputServiceMock.ask("Сколько часов в сутках")).thenReturn("24");
        when(inputServiceMock.ask("Основатель Facebook (Фамилия)")).thenReturn("Цукерберг");
        Assertions.assertEquals(5, examService.checkTest());

    }

    @Test
    @DisplayName("Test failed Default Locale(RU)")
    public void getFailExam(){
        when(inputServiceMock.ask("Третья планета от солнца")).thenReturn("Марс");
        when(inputServiceMock.ask("Год окончания второй мировой войны")).thenReturn("1941");
        when(inputServiceMock.ask("Столица России")).thenReturn("Сочи");
        when(inputServiceMock.ask("Сколько часов в сутках")).thenReturn("30");
        when(inputServiceMock.ask("Основатель Facebook (Фамилия)")).thenReturn("Дуров");
        Assertions.assertNotEquals(5, examService.checkTest());
    }

    @Test
    @DisplayName("Test first name")
    public void getStudentName(){
        String firstName = localizedService.getMessage("name.first");
        when(inputServiceMock.ask(firstName)).thenReturn("Иван");
        studentService.askStudentFirstName();
        student = studentService.getStudent();
        Assertions.assertEquals("Иван", student.getFirstName());
    }

    @Test
    @DisplayName("Test second name")
    public void getStudentSecondName(){
        String secondName = localizedService.getMessage("name.second");
        when(inputServiceMock.ask(secondName)).thenReturn("Иванов");
        studentService.askStudentSecondName();
        student = studentService.getStudent();
        Assertions.assertEquals("Иванов", student.getSecondName());
    }

    @Test
    @DisplayName("Test english questions")
    public void getEnglishLocalQuestions(){
        LocalizedService localizedServiceEn = new LocalizedServiceImpl(Locale.ENGLISH, ms);
        ExamService examServiceEn = new ExamServiceCSV(localizedServiceEn.getCSVPath(), inputServiceMock);
        examServiceEn.readQuestions();
        when(inputServiceMock.ask("Third planet from Sun")).thenReturn("Earth");
        when(inputServiceMock.ask("Year of the end of world war ii")).thenReturn("1945");
        when(inputServiceMock.ask("Capital of Russia")).thenReturn("Moscow");
        when(inputServiceMock.ask("How many hours in days")).thenReturn("24");
        when(inputServiceMock.ask("Founder of Facebook (second name)")).thenReturn("Zuckerberg");
        Assertions.assertEquals(5, examServiceEn.checkTest());
    }
}
