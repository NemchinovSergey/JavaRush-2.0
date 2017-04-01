package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Program arguments: [dir] [file]");
            return;
        }

        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File oldResultFile = new File(resultFileAbsolutePath);
        File newResultFile = new File(oldResultFile.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(oldResultFile, newResultFile);

        try (FileOutputStream writer = new FileOutputStream(oldResultFile.getParent() + "/allFilesContent.txt"))
        {
            List<File> files = new ArrayList<>();

            processDirectory(path, files);

            Collections.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file : files) {
                FileInputStream reader = new FileInputStream(file);

                while (reader.available() > 0)
                {
                    writer.write(reader.read());
                }
                writer.write('\n');
            }
        }
        catch (IOException e) {

        }
    }


    private static void processDirectory(String path, List<File> list) {
        File dir = new File(path);
        for (File file : dir.listFiles())
        {
            if (file.isDirectory()) {
                processDirectory(file.getAbsolutePath(), list);
            }
            else
            {
                if (file.length() > 50) {
                    FileUtils.deleteFile(file);
                }
                else {
                    list.add(file);
                }
            }
        }
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
