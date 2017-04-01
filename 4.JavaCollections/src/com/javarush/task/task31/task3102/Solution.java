package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();

        File rootDirectory = new File(root);

        if (rootDirectory.isDirectory())
        {
            Queue<File> queue = new LinkedList<>();
            queue.offer(rootDirectory);

            while (queue.peek() != null)
            {
                File directory = queue.poll();
                File[] items = directory.listFiles();
                for (File item : items)
                {
                    if (item.isDirectory()) {
                        queue.offer(item);
                    }
                    else
                    {
                        list.add(item.getAbsolutePath());
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<String> files = getFileTree("d:/temp");

        for (String s : files)
            System.out.println(s);
    }
}
