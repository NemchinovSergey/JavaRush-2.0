package com.javarush.task.task06.task0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = Integer.parseInt(reader.readLine());
            String str = Integer.toString(number);

            for (int i = 0; i < str.length(); i++) {
                int n = str.charAt(i) - '0';
                if (n % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
            System.out.println(String.format("Even: %d Odd: %d", even, odd));
        }

    }
}
