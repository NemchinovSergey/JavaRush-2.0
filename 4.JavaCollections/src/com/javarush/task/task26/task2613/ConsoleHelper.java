package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String result = null;
        try {
            result = bis.readLine();
            if (result.toUpperCase().contains("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage("Ведите код валюты (3 символа):");
            String code = readString();
            if (code.length() == 3) {
                return code.toUpperCase();
            } else {
                writeMessage("Введен некорректный код валюты");
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage("Введите номинал банкноты и количество банкнот (2 положительных числа через пробел):");
            String s = readString();
            String[] result = s.split(" ");

            try {
                if (result.length == 2
                        && Integer.parseInt(result[0]) > 0 && Integer.parseInt(result[1]) > 0
                        ) {
                    return result;
                } else writeMessage("Please specify valid data.");
            } catch (NumberFormatException e) {
                writeMessage("Please specify valid data.");
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage("Выберите операцию:");
            writeMessage("\t1 - INFO");
            writeMessage("\t2 - DEPOSIT");
            writeMessage("\t3 - WITHDRAW");
            writeMessage("\t4 - EXIT");
            Operation operation;
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                writeMessage("Введен некорректный номер операции.");
                continue;
            }
            return operation;
        }
    }
}
