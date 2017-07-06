package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] numbers = new int[15];
        int sumOdd = 0;
        int sumEven = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(reader.readLine());
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if ( i == 0 || i % 2 == 0) {
                sumEven += numbers[i];
            }
            else {
                sumOdd += numbers[i];
            }
        }

        if (sumOdd > sumEven) {
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        }
        else if (sumOdd < sumEven) {
            System.out.println("В домах с четными номерами проживает больше жителей.");
        }
        else {
            System.out.println("В домах с четными и нечетными номерами проживает одинаковое количество жителей.");
        }
    }
}
