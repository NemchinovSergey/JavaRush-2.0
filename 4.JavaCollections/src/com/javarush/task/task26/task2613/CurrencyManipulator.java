package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<Integer, Integer>();;  // Map<номинал, количество>

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
        }
        else {
            val += count;
        }
        denominations.put(denomination, val);
    }
}
