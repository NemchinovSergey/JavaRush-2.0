package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            int number = Integer.parseInt(reader.readLine());
            int count = 0;
            int summ = 0;
            while (number != -1) {
                count++;
                summ += number;
                number = Integer.parseInt(reader.readLine());
            }
            double avg = 1.0 * summ / count;
            System.out.println(avg);
        }
    }
}

