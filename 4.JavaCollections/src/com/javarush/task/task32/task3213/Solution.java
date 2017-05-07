package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo
        System.out.println(decode(new StringReader(""), -3));
        System.out.println(decode(null, -3));

    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            char c;
            while ((c = (char) reader.read()) != '\uFFFF') {
                if (c != ' ') {
                    c += key;
                }
                sb.append(c);
            }
        } catch (Exception ignored) {
        }
        return sb.toString();
    }

}
