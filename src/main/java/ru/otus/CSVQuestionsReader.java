package ru.otus;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CSVQuestionsReader {

    public CSVQuestionsReader(String csvPath){
        this.csvPath = csvPath;
    }

    private String csvPath;
    private Map<String, String> answers = new HashMap<String, String>();

    public Map<String, String> getAnswers(){
        return answers;
    }

    public void setAnswers(){
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
                answers.put(nextLine[0], nextLine[1]);
            }
        }
        catch (Exception e)
        {
            System.out.println("Ошибка при получении CSV: " + e.getMessage() + e.getStackTrace());
        }
    }
}