package ru.otus.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ru.otus.Main;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor on 18.12.2018.
 */
public class ExamServiceCSV implements ExamService {

    public ExamServiceCSV(String csvPath, InputService inputService) {
        this.csvPath = csvPath;
        this.inputService = inputService;
    }

    private String csvPath;
    private InputService inputService;
    private Map<String, String> questions = new HashMap<String, String>();
    private Integer correctAnswerCount = 0;

    public Map<String, String> getQuestions() {
        return questions;
    }

    public void readQuestions(){
        try
        {
            final CSVParser parser =
                    new CSVParserBuilder()
                            .withSeparator(';')
                            .withIgnoreQuotations(true)
                            .build();
            final CSVReader reader =
                    new CSVReaderBuilder(new InputStreamReader(Main.class.getClassLoader().
                            getResourceAsStream(csvPath)))
                            .withSkipLines(1)
                            .withCSVParser(parser)
                            .build();
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                questions.put(nextLine[0], nextLine[1]);
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка при получении CSV: " + e.getMessage() + e.getStackTrace());
        }
    }

    public int checkTest() {
        for (Map.Entry<String, String> question : questions.entrySet()) {
            System.out.println(question.getKey());
            String answer = inputService.ask(question.getKey());
            if (question.getValue().equals(answer)) {
                correctAnswerCount++;
            }
        }
        return correctAnswerCount;
    }
}
