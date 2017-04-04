package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(final TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                if (t != null && e != null)
                {
                    String threadName = t.getName();
                    StringBuilder maskedName = new StringBuilder("");
                    for (int i = 0; i < threadName.length(); i++)
                    {
                        maskedName.append("*");
                    }
                    String maskedMsg = e.getMessage().replaceAll(threadName, maskedName.toString());
                    System.out.println(maskedMsg);
                }
            }
        };
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args)
    {
        Solution task1 = new Solution(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("TimerTaskOriginal. Method run()");
                int zero = 0;
                System.out.println(100 / zero);
            }
        });

        Thread thread = new Thread(task1);
        thread.start();

        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {

        }
    }
}