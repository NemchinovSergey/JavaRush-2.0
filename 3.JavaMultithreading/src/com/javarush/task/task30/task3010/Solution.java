package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            String number = args[0];

            for (int radix = 2; radix <= 36; radix++) {
                try {
                    BigInteger big = new BigInteger(number, radix);
                    System.out.println(radix);
                    return;
                } catch (Exception e) {
                }
            }
            System.out.println("incorrect");
        } catch (Exception e) {

        }
    }
}