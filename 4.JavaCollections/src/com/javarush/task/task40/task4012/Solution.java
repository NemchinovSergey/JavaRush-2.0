package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
В Java 8 DateTime API реализовано множество классов и методов, которые существенно упрощают работу со временем и датами.

Реализуем несколько простых методов, чтобы познакомиться с ними поближе.

1) Метод isLeap должен принимать дату и возвращать true, если год является високосным, иначе - false.
2) Метод isBefore должен принимать дату и возвращать true, если она предшествует текущей дате, иначе - false.
3) Метод addTime должен возвращать полученное в качестве параметра время, увеличенное на n СhronoUnit.
4) Метод getPeriodBetween должен принимать две даты и возвращать временной промежуток между ними. Помни, что в метод Period.between необходимо передать сначала меньшую, а затем большую дату.


Требования:
1. Метод isLeap должен принимать дату и возвращать true, если год является високосным, иначе - false.
2. Метод isBefore должен принимать дату и возвращать true, если она предшествует текущей дате, иначе - false.
3. Метод addTime должен возвращать полученное в качестве параметра время, увеличенное на n СhronoUnit.
4. Метод getPeriodBetween должен принимать две даты и возвращать временной промежуток между ними.
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate)
                ? Period.between(firstDate, secondDate)
                : Period.between(secondDate, firstDate);
    }
}
