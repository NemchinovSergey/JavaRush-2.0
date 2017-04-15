package com.javarush.task.task27.task2709;

public class TransferObject {
    protected volatile boolean isValuePresent = false; //use this variable
    private int value;

    public synchronized int get() {
        try {
            // Ждём когда ProducerTask обновит value и установит флаг isValuePresent в true
            while (!isValuePresent) {
                wait();
            }
        } catch (InterruptedException e) {

        }
        isValuePresent = false;
        notifyAll();
        System.out.println("Got: " + value);
        return value;
    }

    public synchronized void put(int value) {
        try {
            // Ждём когда ConsumerTask получит value и установит флаг isValuePresent в false
            while (isValuePresent) {
                wait();
            }
        } catch (InterruptedException e) {

        }
        this.value = value;
        isValuePresent = true;
        notifyAll();
        System.out.println("Put: " + value);
    }
}
