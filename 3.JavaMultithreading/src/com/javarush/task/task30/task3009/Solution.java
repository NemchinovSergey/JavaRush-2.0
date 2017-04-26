package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> radixes = new HashSet<>();

        if (number == null || number.isEmpty()) {
            return radixes;
        }

        try {
            int dec = Integer.parseInt(number, 10);

            for (int i = 2; i <= 36; i++) {
                String value = Integer.toString(dec, i);
                if (isPalindrom(value)) {
                    radixes.add(i);
                }
            }
        } catch (NumberFormatException e) {

        }

        return radixes;
    }

    private static boolean isPalindrom(String number) {
        if (number == null || number.length() < 1)
            return false;

        int a = 0;
        int b = number.length()-1;
        while (a < b) {
            if (number.charAt(a) != number.charAt(b))
                return false;
            a++;
            b--;
        }
        return true;
    }

}