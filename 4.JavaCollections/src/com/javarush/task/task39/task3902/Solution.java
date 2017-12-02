package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
В процессе разработки сложного алгоритма кодирования возникла задача определить четное ли количество единиц в двоичной записи числа.

Реализуй метод boolean isWeightEven(long number), который будет возвращать true или false в зависимости от того,
является ли количество единиц в двоичном представлении числа number четным или нечетным.

P.S. Постарайся использовать только побитовые операции, а также минимизировать время выполнения программы.


Требования:
1. Метод isWeightEven должен возвращать true, если количество единиц в двоичном представлении анализируемого числа четное.
2. Метод isWeightEven должен возвращать false, если количество единиц в двоичном представлении анализируемого числа нечетное.
3. Время выполнения метода isWeightEven должно быть максимально близко к оптимальному.
4. Метод isWeightEven должен быть публичным и статическим.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please type in a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("Number of ones in a given number is " + result);

    }

    /* Этот вариант лучше */
    public static boolean isWeightEven(long number) {
        int count = 0;
        while(number != 0)
        {
            count++;
            //System.out.println("number binary: 0b" + Long.toString(number, 2));
            //System.out.println("number-1 binary: 0b" + Long.toString(number - 1, 2));
            number = number & (number - 1);
        }

        return count % 2 == 0;
    }

    /* Мой вариант */
    /*public static boolean isWeightEven(long number) {
        int counter = 0;
        while (number != 0) {
            //System.out.println("binary: 0b" + Long.toString(number, 2));
            if ((number & 1) == 1) {
                //System.out.println("+1");
                counter++;
            }
            number >>= 1;
        }
        //System.out.println("bits count: " + counter);
        return counter % 2 == 0;
    }*/
}
