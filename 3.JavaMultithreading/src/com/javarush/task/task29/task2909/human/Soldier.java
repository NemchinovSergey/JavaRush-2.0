package com.javarush.task.task29.task2909.human;

/**
 * Created by nemchinov on 05.04.2017.
 */
public class Soldier extends Human {
    protected boolean isSoldier;

    public Soldier(String name, int age) {
        super(name, age);
        this.isSoldier = true;
    }

    @Override
    public void live() {
        if (isSoldier)
            fight();
    }

    public void fight() {
    }
}
