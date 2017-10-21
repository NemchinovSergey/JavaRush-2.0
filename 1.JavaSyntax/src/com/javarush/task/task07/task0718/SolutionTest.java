package com.javarush.task.task07.task0718;

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
    public void testNoOutput() throws Exception {
        String string = "1\n" +
                         "22\n" +
                         "333\n" +
                         "4444\n" +
                         "55555\n" +
                         "666666\n" +
                         "7777777\n" +
                         "88888888\n" +
                         "999999999\n" +
                         "0000000000\n";

        // Подменяем поток на ввод
        System.setIn(new ByteArrayInputStream(string.getBytes()));

        // Запускаем программу
        Solution.main(null);

        // Получаем вывод программы
        String result = outputStream.toString();
        systemOut.println("Result: " + result);

        // Выполняем проверку
        Assert.assertEquals("", result.trim());
    }

    @Test
    public void test3() throws Exception {
        String string = "1\n" +
                        "22\n" +
                        "999999999\n" + // <- test case
                        "4444\n" +
                        "55555\n" +
                        "666666\n" +
                        "7777777\n" +
                        "88888888\n" +
                        "333\n" +
                        "0000000000\n";

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
    public void test9() throws Exception {
        String string = "1\n" +
                        "22\n" +
                        "333\n" +
                        "4444\n" +
                        "55555\n" +
                        "666666\n" +
                        "7777777\n" +
                        "88888888\n" +
                        "0000000000\n" +
                        "999999999\n"; // <- test case

        // Подменяем поток на ввод
        System.setIn(new ByteArrayInputStream(string.getBytes()));

        // Запускаем программу
        Solution.main(null);

        // Получаем вывод программы
        String result = outputStream.toString();
        systemOut.println("Result: " + result);

        // Выполняем проверку
        Assert.assertEquals("9", result.trim());
    }



}