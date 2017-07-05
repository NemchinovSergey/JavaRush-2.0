package com.javarush.task.task10.task1006;

/* 
Задача №6 на преобразование целых типов

Убери ненужные операторы приведения типа, чтобы получился ответ: result: 1000.0
        short b = (short) 45;
        char c = (short) 'c';
        short s = (short) 1005.22;
        int i = (short) 150000;
        float f = (short) 4.10f;
        double d = (short) 1.256d;
        double result = (f * b) + (i / c) - (d * s) + 562.78d;
        System.out.println("result: " + result);
*/

public class Solution {
    public static void main(String[] args) {
        short b = (short) 45;
        char c = (short) 'c';
        short s = (short) 1005.22;
        int i =  150000;
        float f =  4.10f;
        double d =  1.256d;
        double result = (f * b) + (i / c) - (d * s) + 562.78d;
        System.out.println("result: " + result);
    }
}