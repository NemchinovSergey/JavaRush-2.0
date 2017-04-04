package com.javarush.task.task25.task2512;

import java.util.Stack;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Stack<Throwable> stack = new Stack<>();
        stack.push(e);
        Throwable cause = e;
        while (cause.getCause() != null) {
            cause = cause.getCause();
            stack.push(cause);
        }
        while (!stack.empty())
        {
            System.out.println(stack.pop().toString());
        }

    }

    public static void main(String[] args)
    {
        Thread thread = new Thread() {
            {
                setUncaughtExceptionHandler(new Solution());
            }
            @Override
            public void run()
            {
                try{
                    throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
                } catch (Exception e){
                    getUncaughtExceptionHandler().uncaughtException(currentThread(), e);
                }
            }
        };
        thread.start();
    }

}
