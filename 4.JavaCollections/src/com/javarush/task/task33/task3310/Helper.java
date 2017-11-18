package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    /** Генерирует случайную строку */
    public static String generateRandomString() {
        //initialize secure random
        SecureRandom secureRandom = new SecureRandom();

        //create 130 bits random BigInteger
        BigInteger bigInteger = new BigInteger(130, secureRandom);

        //return string representation of BigInteger in 32 radix
        return bigInteger.toString(32);
    }

    /** Выводит переданный текст в консоль */
    public static void printMessage(String message) {
        System.out.println(message);
    }

}
