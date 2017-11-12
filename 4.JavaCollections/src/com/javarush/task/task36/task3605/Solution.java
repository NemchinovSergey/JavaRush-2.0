package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    private static final int SYMBOLS_NUMBER = 5;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            return;
        }

        String fileName = args[0];

        // Загружаем сразу весь файл
        // Для огромных файлов так делать нельзя
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        Set<Character> treeSet = new TreeSet<>();

        // Большие объёмы данных надо обрабатывать порциями, а не так - сразу
        for (Character chr : lines.toString().toLowerCase().toCharArray()) {
            if (isAlphabetChar(chr)) {
                treeSet.add(chr);
            }
        }

        System.out.println(getSetElements(treeSet, SYMBOLS_NUMBER));

        System.out.println();
    }

    private static boolean isAlphabetChar(Character chr) {
        return (chr >= 'a' && chr <= 'z')
                || (chr >= 'A' && chr <= 'Z')
                || (chr >= 'а' && chr <= 'я')
                || (chr >= 'А' && chr <= 'Я');
    }

    private static String getSetElements(Set<Character> set, int num) {
        StringBuilder result = new StringBuilder("");
        int i = 0;
        for (Character chr : set) {
            if (i >= num) {
                return result.toString();
            }

            result.append(chr);
            i++;
        }
        return result.toString();
    }
}
