package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.List;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object someObject = "a string value";
        Integer number = (Integer) someObject * 10;
    }

    public void methodThrowsNullPointerException() {
        List<String> nullList = null;
        System.out.println(nullList.size());
    }

    public static void main(String[] args) {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        try {
            veryComplexClass.methodThrowsClassCastException();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        try {
            veryComplexClass.methodThrowsNullPointerException();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
