package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
Исправь ошибку в методе isPowerOfThree(int n), он должен возвращать true, если n является целочисленной степенью числа 3. Иначе - false.


Требования:
1. Метод isPowerOfThree должен возвращать true, если n является целочисленной степенью числа 3.
2. Метод isPowerOfThree должен возвращать false, если n не является целочисленной степенью числа 3.
3. Метод isPowerOfThree должен быть публичным.
4. Метод isPowerOfThree должен быть статическим.
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while(n > 1)
        {
            if (n % 3 != 0 ) {
                return false;
            }
            n = n / 3;
        }

        return n == 1;
    }
}
