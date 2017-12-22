package com.javarush.task.task28.task2810.model;

/**  Парсит http://hh.ru
     https://novosibirsk.hh.ru/search/vacancy?text=java+Новосибирск
     https://hh.ru/search/vacancy?text=java+ADDITIONAL_VALUE&page=PAGE_VALUE */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
}
