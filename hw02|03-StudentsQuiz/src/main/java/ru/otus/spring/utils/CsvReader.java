package ru.otus.spring.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    public static final String CSV_DELIMITER = ";";

    public static List<List<String>> getCsvLineValues(String path) {
        List<List<String>> lineValues = new ArrayList<>();

        if (path == null || path.isEmpty()) {
            throw new RuntimeException("Error: empty path for CSV");
        }

        try (Scanner lineScanner = new Scanner(CsvReader.class.getResourceAsStream("/" + path))) {
            while (lineScanner.hasNextLine()) {
                List<String> values = new ArrayList<>();
                try (Scanner rowScanner = new Scanner(lineScanner.nextLine())) {
                    rowScanner.useDelimiter(CSV_DELIMITER);
                    while (rowScanner.hasNext()) {
                        values.add(rowScanner.next());
                    }
                }
                lineValues.add(values);
            }
        }
        return lineValues;
    }
}
