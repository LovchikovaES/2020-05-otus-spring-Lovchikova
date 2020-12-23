package ru.otus.spring.csv;

import java.util.ArrayList;
import java.util.List;

public class CsvRow {
    private List<String> rowElements = new ArrayList<>();

    public CsvRow() {
    }

    public void add(String element) {
        rowElements.add(element);
    }

    public String get(int index) throws IndexOutOfBoundsException {
        return rowElements.get(index);
    }

    public int size() {
        return rowElements.size();
    }

    public List<String> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        return rowElements.subList(fromIndex, toIndex);
    }
}
