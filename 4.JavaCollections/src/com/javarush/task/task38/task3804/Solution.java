package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        System.out.println(ExceptionFactory.getException(ExceptionApplicationMessage.UNHANDLED_EXCEPTION));
        System.out.println(ExceptionFactory.getException(ExceptionApplicationMessage.SOCKET_IS_CLOSED));

        System.out.println(ExceptionFactory.getException(ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS));
        System.out.println(ExceptionFactory.getException(ExceptionDBMessage.RESULT_HAS_NOT_GOTTEN_BECAUSE_OF_TIMEOUT));

        System.out.println(ExceptionFactory.getException(ExceptionUserMessage.USER_DOES_NOT_HAVE_PERMISSIONS));
        System.out.println(ExceptionFactory.getException(ExceptionUserMessage.USER_DOES_NOT_EXIST));
    }
}