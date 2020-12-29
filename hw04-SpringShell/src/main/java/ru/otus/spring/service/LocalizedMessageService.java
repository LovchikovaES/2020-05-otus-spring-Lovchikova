package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProperties;

@Service
public class LocalizedMessageService {
    private final MessageSource messageSource;
    private final AppProperties appProperties;

    public LocalizedMessageService(MessageSource messageSource, AppProperties appProperties) {
        this.appProperties = appProperties;
        this.messageSource = messageSource;
    }

    public String getMessage(String bundleProperty) {
        return messageSource.getMessage(bundleProperty, new String[]{}, appProperties.getLocale());
    }

}
