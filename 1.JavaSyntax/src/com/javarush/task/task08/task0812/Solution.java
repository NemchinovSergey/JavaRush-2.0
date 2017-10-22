package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        /* Создаём список */
        ArrayList<Integer> integers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // читаем 10 чисел с консоли
            for (int i = 0; i < 10; i++) {
                integers.add(Integer.parseInt(reader.readLine()));
            }
        }

        int x = integers.get(0);
        int count = 1;
        int max = 1;

        for (int i = 1; i < integers.size(); i++) {
            // Если следующее число повторяется, то увеличиваем счетчик
            if (Objects.equals(integers.get(i), x)) {
                count++;
            }
            else {
                // Если число другое, то начинаем подсчёт заново
                x = integers.get(i);
                count = 1;
            }
            // запоминаем максимальное число повторений
            if (count > max) {
                max = count;
            }
        }

        System.out.println(max);
    }
}