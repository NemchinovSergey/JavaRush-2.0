package com.javarush.task.task35.task3507;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        if (pathToAnimals.contains(":/")) {
            pathToAnimals = pathToAnimals.replaceFirst("/", "");
        }
        System.out.println(pathToAnimals);
        Path path = Paths.get(pathToAnimals);
        File[] files = path.toFile().listFiles();
        for (File f : files) {
            if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
        return null;
    }
}
