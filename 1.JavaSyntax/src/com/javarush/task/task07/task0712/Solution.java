package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 10; i++) {
                list.add(reader.readLine());
            }
        }

        int minLength = Integer.MAX_VALUE;
        int minIndex = 0;
        int maxLength = Integer.MIN_VALUE;
        int maxIndex = 0;

        for (int i = 0; i < 10; i++) {
            String str = list.get(i);

            if (str.length() < minLength) {
                minLength = str.length();
                minIndex = i;
            }

            if (str.length() > maxLength) {
                maxLength = str.length();
                maxIndex = i;
            }
        }

        if (minIndex < maxIndex) {
            System.out.println(list.get(minIndex));
        }
        else {
            System.out.println(list.get(maxIndex));
        }
    }
}
