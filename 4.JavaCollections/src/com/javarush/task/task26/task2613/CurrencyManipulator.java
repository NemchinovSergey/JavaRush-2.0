package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<Integer, Integer>();  // Map<номинал, количество>

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        Integer val = denominations.get(denomination);
        if (val == null) {
            val = count;
        } else {
            val += count;
        }
        denominations.put(denomination, val);
    }

    public int getTotalAmount() {
        int count = 0;
        for (Map.Entry<Integer, Integer> item : denominations.entrySet()) {
            count += item.getKey() * item.getValue();
        }
        return count;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> resMap = new HashMap<>();

        Integer[] banknotes = denominations.keySet().toArray(new Integer[0]);
        Arrays.sort(banknotes);

        // резервирование купюр для выдачи:
        int remainFromExpectedAmount = expectedAmount;
        for (int i = banknotes.length - 1; i >= 0; i--) {
            int selected = 0;
            Integer nominal = banknotes[i];
            int remainFromNominalSum = denominations.get(nominal) - 1;
            remainFromExpectedAmount -= nominal;
            while ((remainFromExpectedAmount >= 0) && (remainFromNominalSum >= 0)) {
                selected++;
                remainFromExpectedAmount -= nominal;
                remainFromNominalSum--;
            }
            remainFromExpectedAmount += nominal;
            if (selected > 0) {
                resMap.put(nominal, selected);
            }
            if (remainFromExpectedAmount == 0) {
                break;
            }
        }

        // купюр не хватило - ошибка:
        if (remainFromExpectedAmount != 0) {
            throw new NotEnoughMoneyException();
        }

        // списание зарезервированных купюр со счета банкомата:
        for (Map.Entry<Integer, Integer> item : resMap.entrySet()) {
            denominations.put(item.getKey(), denominations.get(item.getKey()) - item.getValue());
        }

        return resMap;
    }
}
