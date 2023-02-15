package com.segmaware.utility.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {

    @Autowired
    private MessageSource messageSource;

    public String getLocalizedMessage(String message) {
        StringBuilder errorMessage = new StringBuilder();
        Locale currentLocale = LocaleContextHolder.getLocale();
        errorMessage.append(messageSource.getMessage(message,null, currentLocale));
        return errorMessage.toString();
    }

    public String getLocalizedMessage(String message, Object[] args) {
        StringBuilder errorMessage = new StringBuilder();
        Locale currentLocale = LocaleContextHolder.getLocale();
        errorMessage.append(messageSource.getMessage(message, args, currentLocale));
        return errorMessage.toString();
    }
}
