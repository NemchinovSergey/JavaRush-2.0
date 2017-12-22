package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class DepositCommand implements Command {
    @Override
    public void execute() {
        String currensyCode = ConsoleHelper.askCurrencyCode();
        String[] banknotes = ConsoleHelper.getValidTwoDigits(currensyCode);
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currensyCode);
        currencyManipulator.addAmount(Integer.parseInt(banknotes[0]), Integer.parseInt(banknotes[1]));
    }
}
