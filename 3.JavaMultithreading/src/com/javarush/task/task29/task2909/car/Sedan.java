package com.javarush.task.task29.task2909.car;

/**
 * Created by nemchinov on 05.04.2017.
 */
public class Sedan extends Car {

    public Sedan(int numberOfPassengers) {
        super(Car.SEDAN, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SEDAN_SPEED;
    }
}
