package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    private Switchable switchable;

    public ElectricPowerSwitch(Switchable switchable) {
        this.switchable = switchable;
    }

    public void press() {
        System.out.println("Pressed the power switch.");
        if (switchable.isOn()) {
            switchable.turnOff();
        } else {
            switchable.turnOn();
        }
    }
}
