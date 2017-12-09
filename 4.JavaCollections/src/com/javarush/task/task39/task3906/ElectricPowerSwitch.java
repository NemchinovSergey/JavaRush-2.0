package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    private SecuritySystem securitySystem;

    public ElectricPowerSwitch(SecuritySystem securitySystem) {
        this.securitySystem = securitySystem;
    }

    public void press() {
        System.out.println("Pressed the power switch.");
        if (securitySystem.isOn()) {
            securitySystem.turnOff();
        } else {
            securitySystem.turnOn();
        }
    }
}
