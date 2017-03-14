package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/
public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"closed {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        Collections.sort(list, new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                // compare names
                String name1 = (String) stock1.get("name");
                String name2 = (String) stock2.get("name");
                int result = name1.compareTo(name2);
                if (result != 0) return result;

                // compare dates
                Date date1 = (Date) stock1.get("date");
                Date date2 = (Date) stock2.get("date");
                result = date1.compareTo(date2);
                if (result != 0) return result * -1;

                // compare changes
                if (stock1.containsKey("change") && stock2.containsKey("change"))
                {
                    double change1 = (double) stock1.get("change");
                    double change2 = (double) stock2.get("change");
                    result = Double.compare(change1, change2);
                    if (result != 0) return result * -1;
                }
                else
                if (stock1.containsKey("open") && stock2.containsKey("open") &&
                        stock1.containsKey("last") && stock2.containsKey("last"))
                {
                    double open1 = (double) stock1.get("open");
                    double last1 = (double) stock1.get("last");
                    double open2 = (double) stock2.get("open");
                    double last2 = (double) stock2.get("last");
                    double profit1 = last1 - open1;
                    double profit2 = last2 - open2;
                    result = Double.compare(profit1, profit2);
                    if (result != 0) return result * -1;
                }

                return 0;
            }
        });
    }

    public static class Stock extends HashMap {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 127.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 35.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 502.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 20.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 7.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 49.72));

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 1.64, getRandomDate()));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 2.84, getRandomDate()));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 5.2, getRandomDate()));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 2.36, getRandomDate()));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", .55, getRandomDate()));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 2.56, getRandomDate()));

        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

