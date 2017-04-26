package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String>
{
    private int value;

    public BinaryRepresentationTask(int i)
    {
        this.value = i;
    }

    @Override
    protected String compute()
    {
        int a = value % 2;
        int b = value / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + result;
        }
        return result;
    }
}
