package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array1 = new Integer[] {2, 3, 3, 4, 5, 7, 10}; // mediana = 4
        //System.out.println(Arrays.toString(sort(array1)));

        Integer[] array2 = new Integer[] {2, 3, 3, 6, 7, 10}; // mediana = 4.5
        //System.out.println(Arrays.toString(sort(array2)));

        Integer[] mass = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9}; // mediana = 5
        //System.out.println(Arrays.toString(sort(mass)));
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        if (array == null) return null;
        if (array.length == 1) return array;

        Arrays.sort(array);

        final double mediana;
        if (array.length % 2 == 0) {
            int index1 = array.length / 2 - 1;
            int index2 = array.length / 2;
            mediana = 1.0 * (array[index1] + array[index2]) / 2;
        }
        else
        {
            mediana = array[array.length / 2];
        }

        Comparator<Integer> sortByMediana = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                double value = Math.abs(o1-mediana) - Math.abs(o2-mediana);
                if (value == 0)
                    value = o1 - o2;
                return (int)value;
            }
        };

        Arrays.sort(array, sortByMediana);
        //System.out.println(mediana);
        return array;
    }
}
