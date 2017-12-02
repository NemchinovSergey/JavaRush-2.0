package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please type in a number: ");

        long number = Long.parseLong(reader.readLine()); // 9223372036854775807
        System.out.println("Please type in first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please type in second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        long iValue = number >> i & 1;
        long jValue = number >> j & 1;

        if (iValue != jValue)
            number = (number & ~((1 << i) | (1 << j))) | (iValue << j) | (jValue << i);

        /*System.out.println("number: 0b" + Long.toString(number, 2));

        // выставляем бит
        long iBit = 1 << i;
        long jBit = 1 << j;
        System.out.println("iBit: 0b" + Long.toString(iBit, 2));
        System.out.println("jBit: 0b" + Long.toString(jBit, 2));

        // получаем значение битов и сдвигаем до младшего бита
        // т.е. если бит в позиции установлен, то получаем 1, иначе получаем 0
        long iValue = number >> i & 1; //(number & iBit) >>> i;
        long jValue = number >> j & 1; //(number & jBit) >>> j;

        System.out.println("iValue: 0b" + Long.toString(iValue, 2));
        System.out.println("jValue: 0b" + Long.toString(jValue, 2));

        // обнуляем биты в указанных позициях
        number = number & ~(iBit | jBit);
        System.out.println("zeroed number: 0b" + Long.toString(number, 2) + " (" + number + ")");

        // теперь вставляем биты поменяв их местами
        number = number | jValue << i;
        System.out.println("number: 0b" + Long.toString(number, 2) + " (" + number + ")");

        number = number | iValue << j;
        System.out.println("number: 0b" + Long.toString(number, 2) + " (" + number + ")");*/
        return number;
    }
}
