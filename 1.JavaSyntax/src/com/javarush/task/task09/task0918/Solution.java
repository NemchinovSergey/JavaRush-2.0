package com.javarush.task.task09.task0918;

/* 
Все свои, даже исключения
*/

import java.io.FileNotFoundException;

public class Solution {
    public static void main(String[] args) {
    }

    // checked exceptions
    static class MyException extends RuntimeException {
    }

    static class MyException2 extends MyException {
    }

    // unchecked exceptions
    static class MyException3 extends FileNotFoundException {
    }

    static class MyException4 extends MyException3 {
    }
}

