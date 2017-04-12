package com.javarush.task.task27.task2709;

public class ConsumerTask implements Runnable {
    protected volatile boolean stopped;
    private TransferObject transferObject;

    public ConsumerTask(TransferObject transferObject) {
        this.transferObject = transferObject;
        new Thread(this, "ConsumerTask").start();
    }

    public void run() {
        while (!stopped) {
            synchronized (transferObject) {
                try {
                    while (!transferObject.isValuePresent)
                        transferObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                transferObject.get();
            }
        }
    }

    public void stop() {
        stopped = true;
    }
}