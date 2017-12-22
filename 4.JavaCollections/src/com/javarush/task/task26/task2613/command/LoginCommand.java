package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards;

    {
        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "verifiedCards.properties"))) {
            validCreditCards = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
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
        return validCreditCards.containsKey(number) && pin.equals(validCreditCards.getString(number));
    }
}
