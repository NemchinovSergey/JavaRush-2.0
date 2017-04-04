package com.javarush.task.task26.task2607;

/* 
Вежливость - это искусственно созданное хорошее настроение
*/
public class Solution {
    public static IntegerHolder integerHolder = new IntegerHolder();

    public static class IntegerHolder {
        private int value;

        public synchronized int get()
        {
            return value;
        }

        public synchronized void set(int value)
        {
            this.value = value;
        }
    }

    public static class TestThread extends Thread {
        @Override
        public void run()
        {
            for (int i = 0; i < 1000000; i++)
            {
                synchronized (integerHolder)
                {
                    integerHolder.set(integerHolder.get() + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        integerHolder.set(0);

        Thread thread1 = new TestThread();
        Thread thread2 = new TestThread();
        Thread thread3 = new TestThread();

        thread1.start();
        thread2.start();
        thread3.start();

        while (thread1.getState() != Thread.State.TERMINATED ||
                thread2.getState() != Thread.State.TERMINATED ||
                thread3.getState() != Thread.State.TERMINATED);

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(integerHolder.get());

    }
}
