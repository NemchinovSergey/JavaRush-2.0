package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            wheels = new ArrayList<Wheel>();
            String[] array = loadWheelNamesFromDB();
            if (array.length != 4) throw new IllegalArgumentException();

            for (String wheel : array)
            {
                wheels.add(Wheel.valueOf(wheel));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
            //return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT", "BACK_RIGHT"};
            //return new String[]{"BAD_WHEEL_NAME", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT", "FIVE_WHEEL"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(Arrays.toString(car.wheels.toArray()));
    }
}
