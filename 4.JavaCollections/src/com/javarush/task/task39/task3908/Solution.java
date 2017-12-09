package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        String ss = "asdasdasdasd";
        System.out.println(ss);
        System.out.println(isPalindromePermutation(ss));

        ss = "aaaaсdddd";
        System.out.println(ss);
        System.out.println(isPalindromePermutation(ss));

        ss = "aaaddd";
        System.out.println(ss);
        System.out.println(isPalindromePermutation(ss));
    }

    public static boolean isPalindromePermutation(String s) {
        Map<Character, Integer> map = new HashMap<>();

        // подсчитываем количество сколько каких символов
        char[] chs = s.toLowerCase().toCharArray();
        for (char c : chs) {
            map.merge(c, 1, Integer::sum);
            /* instead of
            if (!map.containsKey(c))
                map.put(c, 1);
            else
                map.put(c, map.get(c) + 1);*/
        }

        if (chs.length % 2 == 0) {
            // если в строке чётное количество символов
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                // если количество хоть одного символа нечетное, то палиндрому не быть
                if (entry.getValue() % 2 == 1) {
                    return false;
                }
            }
        } else {
            // в строке НЕчётное количество символов
            boolean firstRepeat = true;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                // только один символ может иметь нечетное количество
                if (entry.getValue() % 2 == 1) {
                    if (firstRepeat)
                        firstRepeat = false;
                    else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
