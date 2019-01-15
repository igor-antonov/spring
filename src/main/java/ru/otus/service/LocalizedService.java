package ru.otus.service;

import java.util.Locale;

public interface LocalizedService {
    String getMessage(String message);
    String getCSVPath();
    Locale getLocale();
}
