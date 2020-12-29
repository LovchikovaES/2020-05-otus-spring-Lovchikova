package ru.otus.spring.csv;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CsvReader {

    public static final String CSV_DELIMITER = ";";

    public CsvReader() {
    }

    public List<CsvRow> getCsvLineValues(String path) {
        if (path == null || path.isEmpty()) {
            throw new CsvException("Error: empty path for CSV");
        }

        List<CsvRow> lineValues = new ArrayList<>();

        try (Scanner lineScanner = new Scanner(CsvReader.class.getResourceAsStream(path))) {
            while (lineScanner.hasNextLine()) {
                CsvRow csvRow = new CsvRow();
                try (Scanner rowScanner = new Scanner(lineScanner.nextLine())) {
                    rowScanner.useDelimiter(CSV_DELIMITER);
                    while (rowScanner.hasNext()) {
                        csvRow.add(rowScanner.next());
                    }
                }
                lineValues.add(csvRow);
            }
        }
        return lineValues;
    }
}
