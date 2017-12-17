package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public final static String DATE_TIME_FORMAT = "d.M.yyyy H:m:s";
    public final static String DATE_FORMAT = "d.M.yyyy";
    public final static String TIME_FORMAT = "H:m:s";
    
    public static void main(String[] args) {
        //printDate("21.4.2014 15:56:45");
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateType dateType = getDateType(date);
        if (dateType == null || dateType == DateType.WRONG) {
            throw new IllegalArgumentException();
        }

        DateTimeFormatter formatter = dateType.getFormatter();
        LocalDate localDate;
        LocalTime localTime;
        try {
            switch (dateType) {
                case DATE_TIME: {
                    localDate = LocalDate.parse(date, formatter);
                    localTime = LocalTime.parse(date, formatter);
                    printDate(localDate);
                    printTime(localTime);
                    break;
                }
                case DATE:
                    localDate = LocalDate.parse(date, formatter);
                    printDate(localDate);
                    break;
                case TIME:
                    localTime = LocalTime.parse(date, formatter);
                    printTime(localTime);
                    break;
            }

        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    private static void printDate(LocalDate localDate) {
        System.out.println("День: " + localDate.getDayOfMonth());
        System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
        System.out.println("День месяца: " + localDate.getDayOfMonth());
        System.out.println("День года: " + localDate.getDayOfYear());
        System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
        System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
        System.out.println("Месяц: " + localDate.getMonthValue());
        System.out.println("Год: " + localDate.getYear());
    }

    private static void printTime(LocalTime localTime) {
        System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
        System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("Часы дня: " + localTime.getHour());
        System.out.println("Минуты: " + localTime.getMinute());
        System.out.println("Секунды: " + localTime.getSecond());
    }

    private static DateType getDateType(String date) {
        DateTimeFormatter dateFormatter;
        if (date.contains(" ")) {
            try {
                dateFormatter = DateTimeFormatter.ofPattern(DateType.DATE_TIME.getFormat());
                dateFormatter.parse(date);
                return DateType.DATE_TIME;
            } catch (DateTimeParseException ignored) {
            }
        }

        try {
            dateFormatter = DateTimeFormatter.ofPattern(DateType.TIME.getFormat());
            dateFormatter.parse(date);
            return DateType.TIME;
        } catch (DateTimeParseException ignored) {
        }

        try {
            dateFormatter = DateTimeFormatter.ofPattern(DateType.DATE.getFormat());
            dateFormatter.parse(date);
            return DateType.DATE;
        } catch (DateTimeParseException ignored) {
        }

        return DateType.WRONG;
    }

    // решил повыделываться с Enum'ом )
    enum DateType {
        WRONG(""),
        DATE_TIME(DATE_TIME_FORMAT),
        DATE(DATE_FORMAT),
        TIME(TIME_FORMAT);

        private String format;

        DateType(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }

        public DateTimeFormatter getFormatter() {
            return DateTimeFormatter.ofPattern(format);
        }
    }
}
