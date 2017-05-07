package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        if (args.length < 3) {
            return;
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            if (raf.length() != 0) {
                long position = Long.parseLong(args[1]);
                raf.seek(raf.length() >= position ? position : raf.length());
            }
            raf.write(args[2].getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
