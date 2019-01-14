package ru.otus.service;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class LocalizedServiceImpl implements LocalizedService {

    private Locale locale;
    private MessageSource messageSource;

    public LocalizedServiceImpl(Locale locale, MessageSource messageSource){
        this.locale = locale;
        this.messageSource = messageSource;
    }

    public LocalizedServiceImpl(MessageSource messageSource){
        this.locale = Locale.getDefault();
        this.messageSource = messageSource;
    }

    public String getMessage(String message){
        return messageSource.getMessage(message, null, locale);
    }

    @Override
    public String getCSVPath() {
        return messageSource.getMessage("csv.path", null, locale);
    }
}
