package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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


    }
}