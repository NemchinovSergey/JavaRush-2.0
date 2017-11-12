package com.javarush.task.task37.task3706;

import java.util.Arrays;
import java.util.List;

/* 
Давно забытый Array
*/
public class Solution {
    public static void main(String[] args) {
        List<Number> numbers = Arrays.asList(1, 2, 3);
        addDataToList(numbers, getData());
        System.out.println(numbers);
    }

    /** Возвращаём только пустой массив!
     *
     *  Если в массиве будет хоть один элемент, то будет попытка вставить данные в методе addDataToList()
     *  в объект list. Однако он создан с помощью Arrays.asList().
     *  И в этом вся соль задания!
     *  Метод Arrays.asList() возвращает неизменяемую обёртку массива, фиксированного размера!
     *  Если нужно создать нормальный, изменяемый список, то нужно сделать так:
     *
     *  List<Number> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
     *
     * @return Объект массив
     */
    public static Number[] getData() {
        return new Number[] { };
    }

    public static void addDataToList(List<Number> list, Number... data) {
        for (Number number : data) {
            list.add(number);
        }
    }
}
