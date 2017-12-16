package com.javarush.task.task40.task4007;

/*
Работа с датами
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Solution {
    public final static String DATE_TIME_FORMAT = "dd.MM.yy HH:mm:ss";
    public final static String DATE_FORMAT = "dd.MM.yy";
    public final static String TIME_FORMAT = "HH:mm:ss";

    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
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

        try {
            Calendar calendar = Calendar.getInstance();
            Date d = dateType.getFormatter().parse(date);
            calendar.setTime(d);

            switch (dateType) {
                case DATE_TIME:
                    printDate(calendar);
                    printTime(calendar);
                    break;
                case DATE:
                    printDate(calendar);
                    break;
                case TIME:
                    printTime(calendar);
                    break;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void printDate(Calendar calendar) {
        System.out.println("День: " + calendar.get(Calendar.DATE));
        System.out.println("День недели: " + ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1));
        System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
        // месяцы начинаются с нуля!
        System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("Год: " + calendar.get(Calendar.YEAR));
    }

    private static void printTime(Calendar calendar) {
        // возвращяет 0 (AM) или 1 (PM)
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
    }

    private static DateType getDateType(String date) {
        DateFormat dateFormatter;
        if (date.contains(" ")) {
            try {
                dateFormatter = new SimpleDateFormat(DateType.DATE_TIME.getFormat());
                dateFormatter.parse(date);
                return DateType.DATE_TIME;
            } catch (ParseException ignored) {
            }
        }

        try {
            dateFormatter = new SimpleDateFormat(DateType.TIME.getFormat());
            dateFormatter.parse(date);
            return DateType.TIME;
        } catch (ParseException ignored) {
        }

        try {
            dateFormatter = new SimpleDateFormat(DateType.DATE.getFormat());
            dateFormatter.parse(date);
            return DateType.DATE;
        } catch (ParseException ignored) {
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

        public DateFormat getFormatter() {
            return new SimpleDateFormat(format);
        }
    }
}
