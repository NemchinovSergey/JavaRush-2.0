package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int max = 0;
        Set<Character> set = new HashSet<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            for (int j = i; j < charArray.length; j++) {
                char c = charArray[j];
                if (!set.contains(c)) {
                    set.add(c);
                } else {
                    if (set.size() > max) {
                        max = set.size();
                    }
                    set = new HashSet<>();
                }
            }
        }
        if (set.size() > max) {
            max = set.size();
        }
        return max;
    }
}
