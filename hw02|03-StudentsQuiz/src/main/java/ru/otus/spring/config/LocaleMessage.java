package ru.otus.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocaleMessage {
    private final MessageSource messageSource;
    private final AppProperties appProperties;

    public LocaleMessage(MessageSource messageSource, AppProperties appProperties) {
        this.appProperties = appProperties;
        this.messageSource = messageSource;
    }

    public String getMessage(String bundleProperty) {
        return messageSource.getMessage(bundleProperty, new String[]{}, appProperties.getLocale());
    }

}
