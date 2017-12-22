package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    /*{
        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "verifiedCards.properties"))) {
            validCreditCards = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CashMachine.RESOURCE_PATH + "login_en.properties"))) {
            res = new PropertyResourceBundle(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (number.length() != 12 || pin.length() != 4) {
                ConsoleHelper.writeMessage(String.format(res.getString("try.again.with.details"), number));
                continue;
            }
            if (!cardIsOk(number, pin)) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
                ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"), number));
                continue;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
            return;
        }
    }

    private boolean cardIsOk(String number, String pin) {
        return validCreditCards.containsKey(number) && pin.equals(validCreditCards.getString(number));
    }
}
