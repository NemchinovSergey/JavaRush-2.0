package com.javarush.task.task26.task2603;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            for (Comparator comparator : comparators) {
                int result = comparator.compare(o1, o2);
                if (result != 0) return result;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("BongString2");
        strings.add("DongString4");
        strings.add("String3");
        strings.add("String1");
        strings.add("AongString1");
        strings.add("String2");
        strings.add("CongString3");
        strings.add("String4");

        @SuppressWarnings("unchecked")
        CustomizedComparator<String> cc = new CustomizedComparator<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() - o2.length();
                    }
                },
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        Collections.sort(strings, cc);

        for (String s : strings) {
            System.out.println(s);
        }
    }
}
