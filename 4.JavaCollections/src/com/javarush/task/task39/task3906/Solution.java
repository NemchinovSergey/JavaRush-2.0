package com.javarush.task.task39.task3906;

/* 
Интерфейсы нас спасут!
*/
public class Solution {
    public static void main(String[] args) {
        //Switchable securitySystem = new SecuritySystem();
        Switchable lightBulb = new LightBulb();
        ElectricPowerSwitch electricPowerSwitch = new ElectricPowerSwitch(lightBulb);

        electricPowerSwitch.press();
        electricPowerSwitch.press();
    }
}
