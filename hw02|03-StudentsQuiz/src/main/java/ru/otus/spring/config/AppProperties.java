package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProperties {

    private Locale locale;
    private int quantityCorrectAnswersToPass;
    private String csvPath;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getQuantityCorrectAnswersToPass() {
        return quantityCorrectAnswersToPass;
    }

    public void setQuantityCorrectAnswersToPass(int quantityCorrectAnswersToPass) {
        this.quantityCorrectAnswersToPass = quantityCorrectAnswersToPass;
    }

    public String getCsvPath() {
        return String.format(this.csvPath, getLocale());
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }
}
