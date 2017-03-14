package com.javarush.task.task31.task3101;

import java.io.File;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        //if (args.length == 0) return;

        String path = "d:/";//args[0];
        String resultFileAbsolutePath = "d:/task3101.txt";//args[1];

        File dir = new File(path);

        for (File file : dir.listFiles()) {
            if (file.isFile()) {

            }
            System.out.println(file.getName());
        }
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
