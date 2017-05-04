package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        if (args.length < 3) {
            return;
        }

        String fileName = args[0];
        String positionStr = args[1];
        String text = args[2];

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            int position = Integer.parseInt(positionStr);
            raf.seek(position);

            byte[] buffer = new byte[text.length()];
            raf.read(buffer, 0, buffer.length);

            String textFromFile = convertByteToString(buffer);

            raf.seek(raf.length());
            raf.write(textFromFile.equals(text) ? "true".getBytes() : "false".getBytes());
        } catch (IOException ignored) {
        }
    }

    public static String convertByteToString(byte readBytes[])
    {
        return new String(readBytes);
    }
}
