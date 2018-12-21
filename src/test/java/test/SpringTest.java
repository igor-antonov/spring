package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.ExamService;
import ru.otus.CSVQuestionsReader;
import ru.otus.QuestionScanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Igor on 18.12.2018.
 */
public class SpringTest {

    private static ExamService examService;
    private static CSVQuestionsReader myCSVReader;

    @BeforeAll
    public static void prepareTest(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/context.xml");
        myCSVReader = context.getBean(CSVQuestionsReader.class);
        myCSVReader.setAnswers();
        examService = context.getBean(ExamService.class);
        //myCSVReader = new CSVQuestionsReader("Questions.csv");
        //myCSVReader.setAnswers();

    }

    @Test
    @DisplayName("Quantity of Questions")
    public void getQuantityQuestions(){
        int quantityQuestions = myCSVReader.getAnswers().size();
        Assertions.assertEquals(5, quantityQuestions);
    }

    @Test
    @DisplayName("Test passed")
    public void getPassExam(){
        //examService = new ExamService(myCSVReader);
        QuestionScanner scannerMock = mock(QuestionScanner.class);
        when(scannerMock.ask("Третья планета от солнца")).thenReturn("Земля");
        when(scannerMock.ask("Год окончания второй мировой войны")).thenReturn("1945");
        when(scannerMock.ask("Столица России")).thenReturn("Москва");
        when(scannerMock.ask("Сколько часов в сутках")).thenReturn("24");
        when(scannerMock.ask("Основатель Facebook (Фамилия)")).thenReturn("Цукерберг");
        Assertions.assertEquals(5, examService.checkTest(scannerMock));

    }

    @Test
    @DisplayName("Test failed")
    public void getFailExam(){
        //examService = new ExamService(myCSVReader);
        QuestionScanner scannerMock = mock(QuestionScanner.class);
        when(scannerMock.ask("Третья планета от солнца")).thenReturn("Марс");
        when(scannerMock.ask("Год окончания второй мировой войны")).thenReturn("1941");
        when(scannerMock.ask("Столица России")).thenReturn("Сочи");
        when(scannerMock.ask("Сколько часов в сутках")).thenReturn("30");
        when(scannerMock.ask("Основатель Facebook (Фамилия)")).thenReturn("Дуров");
        Assertions.assertNotEquals(5, examService.checkTest(scannerMock));
    }
}
