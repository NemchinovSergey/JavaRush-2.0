package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        try {
            StringWriter sw = new StringWriter();
            char[] buff = new char[8 * 1024];

            InputStreamReader inputStreamReader = new InputStreamReader(is);
            while (inputStreamReader.ready()) {
                int count = inputStreamReader.read(buff);
                sw.write(buff, 0, count);
            }
            return sw;
        }
        catch (Exception e) {
            return new StringWriter();
        }
    }
}