package ru.otus;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        try {
            CSVReaderBuilder csvReader = new CSVReaderBuilder(null);//(new FileReader("Questions.csv"),';', '"',1);
        }
        catch (Exception e){

        }
    }
}
