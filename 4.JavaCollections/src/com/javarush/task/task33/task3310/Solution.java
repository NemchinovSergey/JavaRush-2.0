package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    /** Возвращает множество идентификаторов для переданного множества строк  */
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> idsList = new HashSet<>();
        for (String s : strings) {
            idsList.add(shortener.getId(s));
        }
        return idsList;
    }

    /** Возвращает множество строк, которое соответствует переданному множеству идентификаторов */
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys) {
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    /** Тестирует работу переданной стратегии на определенном количестве элементов elementsNumber
     * @param strategy Стратегия хранения
     * @param elementsNumber Количество создаваемых элементов во время теста
     */
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> stringsOriginal = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringsOriginal.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date timer = new Date();
        Set<Long> idsList = getIds(shortener, stringsOriginal);

        long timeGetIds = new Date().getTime() - timer.getTime();
        Helper.printMessage(Long.toString(timeGetIds));

        timer = new Date();
        Set<String> stringsReturned = getStrings(shortener, idsList);
        long timeGetStrings = new Date().getTime() - timer.getTime();
        Helper.printMessage(Long.toString(timeGetStrings));

        boolean success = stringsOriginal.equals(stringsReturned);
        Helper.printMessage(success ? "Тест пройден." : "Тест не пройден.");
    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10_000);
        testStrategy(new OurHashMapStorageStrategy(), 10_000);
        testStrategy(new FileStorageStrategy(), 10_000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10_000);
    }
}
