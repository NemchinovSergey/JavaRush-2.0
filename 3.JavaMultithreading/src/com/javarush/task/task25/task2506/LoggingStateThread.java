package com.javarush.task.task25.task2506;

/**
 * Created by nemchinov on 03.04.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread thread)
    {
        this.thread = thread;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        Thread.State state = thread.getState();
        System.out.println(state);
        while (state != Thread.State.TERMINATED)
        {
            if (state != thread.getState())
            {
                state = thread.getState();
                System.out.println(state);
            }
        }
    }
}
