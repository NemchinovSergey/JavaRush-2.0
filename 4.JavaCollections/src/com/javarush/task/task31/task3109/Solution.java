package com.javarush.task.task31.task3109;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Path pathFile = Paths.get(fileName);
        Properties prop = new Properties();
        try {
            if (fileName.toLowerCase().endsWith(".xml"))
                prop.loadFromXML(Files.newInputStream(pathFile));
            else {
                prop.load(Files.newInputStream(pathFile));
            }
            return prop;
        } catch (Exception e) {
            return new Properties();
        }
    }
}
