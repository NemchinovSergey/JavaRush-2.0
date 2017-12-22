package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res;

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "common_en.properties"))) {
            res = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            writeMessage(res.getString("choose.currency.code"));  //"Ведите код валюты (3 символа):"
            String code = readString();
            if (code.length() == 3) {
                return code.toUpperCase();
            } else {
                writeMessage(res.getString("invalid.data"));  //"Введен некорректный код валюты"
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));  //"Введите номинал банкноты и количество банкнот (2 положительных числа через пробел):"
            String s = readString();
            String[] result = s.split(" ");

            try {
                if (result.length == 2
                        && Integer.parseInt(result[0]) > 0 && Integer.parseInt(result[1]) > 0
                        ) {
                    return result;
                } else writeMessage(res.getString("invalid.data"));  //"Please specify valid data."
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));  //"Please specify valid data."
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("\t1 - " + res.getString("operation.INFO"));
            writeMessage("\t2 - " + res.getString("operation.DEPOSIT"));
            writeMessage("\t3 - " + res.getString("operation.WITHDRAW"));
            writeMessage("\t4 - " + res.getString("operation.EXIT"));
            Operation operation;
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));  //"Введен некорректный номер операции."
                continue;
            }
            return operation;
        }
    }
}
