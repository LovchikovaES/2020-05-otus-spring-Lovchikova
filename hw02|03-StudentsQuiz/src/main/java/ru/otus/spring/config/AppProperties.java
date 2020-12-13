package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProperties {

    private Locale locale;
    private int quantityCorrectAnswersToPass;

    public Locale getLocale() {
        return locale;
    }

    public int getQuantityCorrectAnswersToPass() {
        return quantityCorrectAnswersToPass;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setQuantityCorrectAnswersToPass(int quantityCorrectAnswersToPass) {
        this.quantityCorrectAnswersToPass = quantityCorrectAnswersToPass;
    }
}
