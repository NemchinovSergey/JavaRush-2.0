package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class Solution {
    public static void main(String[] args) {
        MaleFactory maleFactory = new MaleFactory();
        System.out.println(maleFactory.getPerson(99));
        System.out.println(maleFactory.getPerson(4));
        System.out.println(maleFactory.getPerson(15));

        FemaleFactory femaleFactory = new FemaleFactory();
        System.out.println(femaleFactory.getPerson(99));
        System.out.println(femaleFactory.getPerson(12));
        System.out.println(femaleFactory.getPerson(5));
        System.out.println(femaleFactory.getPerson(19));
    }
}
