package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getException(Enum exceptionType) {
        if (exceptionType == null) {
            return new IllegalArgumentException();
        }

        String message = exceptionType.name().substring(0, 1).toUpperCase() + exceptionType.name().substring(1).toLowerCase();
        message = message.replace("_", " ");

        if (exceptionType instanceof ExceptionApplicationMessage) {
            return new Exception(message);
        }

        if (exceptionType instanceof ExceptionDBMessage) {
            return new RuntimeException(message);
        }

        if (exceptionType instanceof ExceptionUserMessage) {
            return new Error(message);
        }

        return new IllegalArgumentException();
    }
}
