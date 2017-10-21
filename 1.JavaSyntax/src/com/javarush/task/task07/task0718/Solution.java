package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        /* Создаём список */
        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // вводим 10 строк текста
            for (int i = 0; i < 10; i++) {
                strings.add(reader.readLine());
            }
        }

        /* Проверяем на возрастание длины */
        int len = 0; // Задаём начальную длину

        for (int i = 0; i < strings.size(); i++) {
            // Берём строку из списка
            String line = strings.get(i);
            // Если длина меньше предыдущей строки
            if (line.length() < len) {
                System.out.println(i);
                break;
            }
            // Запоминаем длину этой строки
            // и переходим к следующей строке
            len = line.length();
        }

    }
}

