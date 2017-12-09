package com.javarush.task.task39.task3906;

public class SecuritySystem {
    private boolean on = false;

    public boolean isOn() {
        return on;
    }

    void turnOff() {
        System.out.println("SecuritySystem is turning off!");
        on = false;
    }

    void turnOn() {
        System.out.println("SecuritySystem is turning on!");
        on = true;
    }
}
