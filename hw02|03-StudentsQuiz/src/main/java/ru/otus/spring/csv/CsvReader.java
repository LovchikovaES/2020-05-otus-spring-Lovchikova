package ru.otus.spring.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    public static final String CSV_DELIMITER = ";";
    public final String path;

    public CsvReader(String path) {
        if (path == null || path.isEmpty()) {
            throw new RuntimeException("Error: empty path for CSV");
        }
        this.path = path;
    }

    public List<CsvRow> getCsvLineValues() {
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
