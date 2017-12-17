package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
        System.out.println(weekDayOfBirthday("1.12.2015", "2016"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate birthdayDate = LocalDate.parse(birthday, formatter);

        // сборку даты вручную валидатор не принимает!
        // LocalDate newDate = LocalDate.of(Year.parse(year).getValue(), birthdayDate.getMonthValue(), birthdayDate.getDayOfMonth());

        LocalDate newDate = birthdayDate.with(Year.parse(year));
        return newDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}
