package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    /*{
        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "deposit_en.properties"))) {
            res = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        try {
            String currensyCode = ConsoleHelper.askCurrencyCode();

            String[] banknotes = ConsoleHelper.getValidTwoDigits(currensyCode);

            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currensyCode);
            int nominal = Integer.parseInt(banknotes[0]);
            int count = Integer.parseInt(banknotes[1]);
            currencyManipulator.addAmount(nominal, count);

            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), nominal * count, currensyCode));
        } catch (NumberFormatException ex) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
