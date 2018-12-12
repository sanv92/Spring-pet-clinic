//package com.example.demo.formatters;
//
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//@Component
//public class LocalDateFormatter implements Formatter<LocalDate> {
//    @Override
//    public String print(LocalDate localDate, Locale locale) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        return localDate.format(formatter);
//    }
//
//    @Override
//    public LocalDate parse(String date, Locale locale) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        return LocalDate.parse(date, formatter);
//    }
//}
