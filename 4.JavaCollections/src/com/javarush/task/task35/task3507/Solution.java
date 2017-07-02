package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        //Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        Set<? extends Animal> allAnimals = getAllAnimals("D:\\DEV\\MyJavaRush 2.0\\JavaRushTasks\\out\\production\\4.JavaCollections\\com\\javarush\\task\\task35\\task3507\\data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> resultSet = new HashSet<>();

        if (pathToAnimals.contains(":/")) {
            pathToAnimals = pathToAnimals.replaceFirst("/", "");
        }
        System.out.println(pathToAnimals);
        Path path = Paths.get(pathToAnimals);
        File[] files = path.toFile().listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile() && f.toString().toLowerCase().endsWith(".class")) {
                    System.out.println(f.toString());
                    try {
                        String className = f.getName().replace(".class", "");
                        Class clazz = Class.forName("com.javarush.task.task35.task3507.data." + className);

                        System.out.println(clazz.getCanonicalName());

                        if (hasPublicConstructor(clazz) && isAnimal(clazz)) {
                            System.out.println(className + " has default public constructor!");
                            resultSet.add((Animal)clazz.newInstance());
                        }

                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return resultSet;
    }

    private static boolean hasPublicConstructor(Class clazz) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            if (c.getParameterCount() == 0 && Modifier.isPublic(c.getModifiers())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAnimal(Class clazz) {
        return Animal.class.isAssignableFrom(clazz);
    }
}
