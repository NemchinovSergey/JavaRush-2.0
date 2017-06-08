package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class Cook extends Observable implements Observer
{
    private String name;
    private static Logger logger = Logger.getLogger(Cook.class.getName());

    public Cook(String name)
    {
        this.name = name;
    }

    public void update(Observable tablet, Object arg)
    {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime()));
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
