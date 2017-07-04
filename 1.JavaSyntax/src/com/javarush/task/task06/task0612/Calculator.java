package com.javarush.task.task06.task0612;

/* 
Калькулятор
*/

public class Calculator {
    public static int plus(int a, int b) {
        //напишите тут ваш код
        return a + b;
    }

    public static int minus(int a, int b) {
        //напишите тут ваш код
        return a - b;
    }

    public static int multiply(int a, int b) {
        //напишите тут ваш код
        return a * b;
    }

    public static double division(int a, int b) {
        //напишите тут ваш код
        return (double) a / b;
    }

    public static double percent(int a, int b) {
        //напишите тут ваш код
        return a * b / 100;
    }

    public static void main(String[] args) {
        System.out.println("Calculator.plus(5, 4) = " + Calculator.plus(5, 4));
        System.out.println("Calculator.minus(5, 4) = " + Calculator.minus(5, 4));
        System.out.println("Calculator.multiply(5, 4) = " + Calculator.multiply(5, 4));
        System.out.println("Calculator.division(30, 4) = " + Calculator.division(30, 4));
        System.out.println("Calculator.percent(200, 10) = " + Calculator.percent(200, 10));
    }
}