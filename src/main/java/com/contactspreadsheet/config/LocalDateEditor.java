package com.contactspreadsheet.config;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateEditor extends PropertyEditorSupport {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	setValue(LocalDate.parse(text, formatter));
    }
}