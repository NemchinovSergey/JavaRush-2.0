package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        //напишите тут ваш код
        String str = Integer.toString(number);

        int summa = 0;

        for (int i = 0; i < str.length(); i++) {
            summa += str.charAt(i) - '0';
        }

        return summa;
    }
}