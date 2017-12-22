package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    /*{
        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "info_en.properties"))) {
            res = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
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
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
