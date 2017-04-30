package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder result = new StringBuilder();

        if (b > a) {
            for (int i = a; i <= b; i++) {
                result.append(i).append(" ");
            }
        } else if (a > b) {
            for (int i = a; i >= b; i--) {
                result.append(i).append(" ");
            }
        } else {
            return String.valueOf(a);
        }

        if (result.length() > 0 && result.charAt(result.length()-1) == ' ')
            result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}