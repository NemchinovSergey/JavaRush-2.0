package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.Operation;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap;
    static {
        allKnownCommandsMap = new HashMap<>();
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {}

    public static final void execute(Operation operation) {
        Command command = allKnownCommandsMap.get(operation);
        if (command != null) {
            command.execute();
        }
    }
}
