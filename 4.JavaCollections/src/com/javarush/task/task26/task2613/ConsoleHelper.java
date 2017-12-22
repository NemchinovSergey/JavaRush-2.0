package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String res = null;
        try {
            res = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String askCurrencyCode() {
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

    public static String[] getValidTwoDigits(String currencyCode) {
        while (true) {
            writeMessage("Введите номинал банкноты и количество банкнот (2 положительных числа через пробел):");
            String s = readString();
            String[] res = s.split(" ");
            if (res.length != 2) {
                writeMessage("Ошибка ввода. Повторите.");
            } else {
                try {
                    int value = Integer.parseInt(res[0]);
                } catch (NumberFormatException e) {
                    writeMessage("Введен некорректный номинал банкноты.");
                    continue;
                }
                try {
                    int value = Integer.parseInt(res[0]);
                } catch (NumberFormatException e) {
                    writeMessage("Введено некорректное количество банкнот.");
                    continue;
                }
                return res;
            }
        }
    }

    public static Operation askOperation() {
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
