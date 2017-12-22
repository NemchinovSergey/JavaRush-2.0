package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private static final String testCardNum = "123456789012";
    private static final String testPin = "1234";

    @Override
    public void execute() throws InterruptOperationException {
        while(true) {
            ConsoleHelper.writeMessage("Введите номер карты (12 цифр):");
            String number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите пин-код (4 цифры):");
            String pin = ConsoleHelper.readString();
            if (number.length() != 12) {
                ConsoleHelper.writeMessage("Ошибка формата номера карты.");
                continue;
            }
            if (pin.length() != 4) {
                ConsoleHelper.writeMessage("Ошибка формата пин-кода.");
                continue;
            }
            if (!cardIsOk(number, pin)) {
                continue;
            }
            ConsoleHelper.writeMessage("Верификация прошла успешно.");
            return;
        }
    }

    private boolean cardIsOk(String number, String pin) {
        if (testCardNum.equals(number) && testPin.equals(pin))
            return true;
        else {
            //ConsoleHelper.writeMessage("Неверный номер карты или пин-код.");
            return false;
        }
    }
}
