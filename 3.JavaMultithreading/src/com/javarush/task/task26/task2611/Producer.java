package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nemchinov on 05.04.2017.
 */
public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            int value = 1;
            while (!Thread.currentThread().isInterrupted()) {
                map.put(String.valueOf(value), "Some text for " + value);
                value++;
                Thread.sleep(500);
            }
        }
        catch (InterruptedException e) {
            System.out.println(String.format("%s thread was terminated", Thread.currentThread().getName()));
        }
    }
}
