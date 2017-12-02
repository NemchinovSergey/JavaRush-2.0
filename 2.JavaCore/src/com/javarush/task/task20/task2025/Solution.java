package com.javarush.task.task20.task2025;

import java.util.TreeSet;

/*
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.


Требования:
1. В классе Solution должен присутствовать метод getNumbers c одним параметром типа long.
2. Метод getNumbers должен быть публичным.
3. Метод getNumbers должен быть статическим.
4. Метод getNumbers должен возвращать массив чисел удовлетворяющих условию задачи.
*/
public class Solution {

    public static long[] getNumbers(long N) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 1; i < N; i++) {
            if (isUnique(i)) {
                int sum = powerAndSum(i);
                if (isArmstrong(sum) && sum < N) {
                    set.add(sum);
                }
            }
        }

        long[] result = new long[set.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = set.pollFirst();
        }

        return result;
    }

    /** Проверяет является ли параметр числом Армстронга — натуральным числом,
     * которое в данной системе счисления равно сумме своих цифр,
     * возведённых в степень, равную количеству его цифр.
     *
     * @param a целое число
     * @return true - если параметр является числом Армстронга
     */
    public static boolean isArmstrong(int a) {
        return a == powerAndSum(a);
    }

    public static boolean isUnique(int b) {
        int rightDigit = b % 10;
        int currentDigit;
        int a = b / 10;

        while (a != 0) {
            currentDigit = a % 10;
            if (currentDigit > rightDigit && rightDigit != 0) {
                return false;
            }
            else {
                rightDigit = currentDigit;
                a = a / 10;
            }
        }
        return true;
    }

    /** Возвращает сумму чисел возведенных в степень количества цифр в параметре {@code а} <br>
     *  Например: а = 123 <br>
     *  Результат: sum = 1*1*1 + 2*2*2 + 3*3*3 = 36
     *
     * @param a целое число
     * @return целое число
     */
    public static int powerAndSum(int a) {
        int digits = String.valueOf(a).length();

        int sum = 0;
        int b = a;
        while (b != 0) {
            int t = b % 10; // остаток от деления на 10 - получаем последнюю цифру в числе
            int c = t;
            for (int i = 1; i < digits; i++) {
                t *= c;
            }
            sum += t;
            b = b / 10; // целочисленное деление на 10 - отбрасываем последнюю цифру в числе
        }
        return sum;
    }

    public static void main(String[] args) {
        // начальная память
        long memoryStart = Runtime.getRuntime().freeMemory();
        // время начала вычислений
        long start = System.currentTimeMillis();

        // вычисляем числа
        long[] a = getNumbers(1000000000);

        // время окончания
        long end = System.currentTimeMillis();
        // память после вычислений
        long memoryEnd = Runtime.getRuntime().freeMemory();

        // выводим найденные числа
        for (long n: a) {
            System.out.println(n);
        }

        // выводим результат
        System.out.println(end - start + " ms");
        System.out.println(((memoryStart - memoryEnd) / (8 * 1024 * 1024) + " MB"));


    }
}
