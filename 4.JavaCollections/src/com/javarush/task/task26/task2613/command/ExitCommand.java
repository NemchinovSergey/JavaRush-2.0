package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res;

    {
        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "exit_en.properties"))) {
            res = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String userInput = ConsoleHelper.readString();

        if ("y".equals(userInput) || "Y".equals(userInput)) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        } else {
            throw new InterruptOperationException();
        }
    }
}
