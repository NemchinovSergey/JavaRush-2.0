package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command {
    @Override
    public void execute() {
        int totalAmount = 0;
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        for (CurrencyManipulator currencyManipulator : currencyManipulators) {
            int currencyManipulatorAmount = currencyManipulator.getTotalAmount();
            totalAmount += currencyManipulatorAmount;
            if (currencyManipulatorAmount > 0) {
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulatorAmount);
            }
        }
        if (totalAmount == 0) {
            ConsoleHelper.writeMessage("No money available.");
        }
    }
}
