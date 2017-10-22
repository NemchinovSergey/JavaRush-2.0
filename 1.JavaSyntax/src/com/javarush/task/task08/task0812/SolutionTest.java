package com.javarush.task.task08.task0812;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SolutionTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    private InputStream systemIn;
    private PrintStream systemOut;

    @Before
    public void setUp() throws Exception {
        // Запоминаем системные потоки ввода-вывода
        systemIn = System.in;
        systemOut = System.out;

        // Теперь создаём поток на запись
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);

        // Подменяем поток на вывод - теперь весь вывод будет печататься в наш поток
        System.setOut(printStream);
    }

    @Test
    public void test10() throws Exception {
        String string = "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "1\n" +
                        "1";

        // Подменяем поток на ввод
        System.setIn(new ByteArrayInputStream(string.getBytes()));

        // Запускаем программу
        Solution.main(null);

        // Получаем вывод программы
        String result = outputStream.toString();
        systemOut.println("Result: " + result);

        // Выполняем проверку
        Assert.assertEquals("10", result.trim());
    }

    @Test
    public void test3() throws Exception {
        String string = "2\n" +
                        "4\n" +
                        "4\n" +
                        "4\n" +
                        "8\n" +
                        "8\n" +
                        "9\n" +
                        "12\n" +
                        "12\n" +
                        "14\n";

        // Подменяем поток на ввод
        System.setIn(new ByteArrayInputStream(string.getBytes()));

        // Запускаем программу
        Solution.main(null);

        // Получаем вывод программы
        String result = outputStream.toString();
        systemOut.println("Result: " + result);

        // Выполняем проверку
        Assert.assertEquals("3", result.trim());
    }

    @Test
    public void test1() throws Exception {
        String string = "1\n" +
                        "2\n" +
                        "3\n" +
                        "11\n" +
                        "14\n" +
                        "15\n" +
                        "27\n" +
                        "78\n" +
                        "12\n" +
                        "3\n";

        // Подменяем поток на ввод
        System.setIn(new ByteArrayInputStream(string.getBytes()));

        // Запускаем программу
        Solution.main(null);

        // Получаем вывод программы
        String result = outputStream.toString();
        systemOut.println("Result: " + result);

        // Выполняем проверку
        Assert.assertEquals("1", result.trim());
    }

}