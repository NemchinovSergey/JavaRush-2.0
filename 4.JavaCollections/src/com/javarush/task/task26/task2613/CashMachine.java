package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        String currensyCode = ConsoleHelper.askCurrencyCode();
        String[] banknotes = ConsoleHelper.getValidTwoDigits(currensyCode);

        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currensyCode);
        currencyManipulator.addAmount(Integer.parseInt(banknotes[0]), Integer.parseInt(banknotes[1]));
    }
}
