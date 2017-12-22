package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Arrays;
import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true) {
            ConsoleHelper.writeMessage("Введите сумму:");
            try {
                int userSum = Integer.parseInt(ConsoleHelper.readString());
                if (userSum <= 0) {
                    ConsoleHelper.writeMessage("Неправильная сумма (отрицательное число).");
                    continue;
                }
                if (!currencyManipulator.isAmountAvailable(userSum)) {
                    ConsoleHelper.writeMessage("Недостаточно средств в банкомате.");
                    continue;
                }

                Map<Integer, Integer> banknotesMap = currencyManipulator.withdrawAmount(userSum);
                Integer[] banknotes = banknotesMap.keySet().toArray(new Integer[0]);
                Arrays.sort(banknotes);

                StringBuilder sb = new StringBuilder();
                for (int i = banknotes.length - 1; i >= 0; i--) {
                    Integer nominal = banknotes[i];
                    int value = banknotesMap.get(nominal);
                    sb.delete(0, sb.length());
                    sb.append("\t").append(nominal).append(" - ").append(value);
                    ConsoleHelper.writeMessage(sb.toString());
                }
                ConsoleHelper.writeMessage("Транзакция успешно завершена.");
                return;
            }
            catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Ошибка ввода суммы.");
            }
            catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Недостаточно купюр для завершения транзакции.");
            }
        }
    }
}
