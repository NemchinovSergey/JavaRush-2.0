package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    // for JavaRush validation:
    public static final String RESOURCE_PATH = "./" + CashMachine.class.getPackage().getName().replace('.', '/') + "/resources/";

    //for local testing with IDE:
    //public static final String RESOURCE_PATH = "_resources/";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        Operation operation;
        try {
            CommandExecutor.execute(Operation.LOGIN);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (true);
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("До встречи!");
        }
    }
}
