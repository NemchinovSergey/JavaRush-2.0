package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        //Set<? extends Animal> allAnimals = getAllAnimals("D:\\DEV\\MyJavaRush 2.0\\JavaRushTasks\\out\\production\\4.JavaCollections\\com\\javarush\\task\\task35\\task3507\\data");
        //Set<? extends Animal> allAnimals = getAllAnimals("D:\\DEV\\JavaRush\\JavaRushTasks\\out\\production\\4.JavaCollections\\com\\javarush\\task\\task35\\task3507\\data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> resultSet = new HashSet<>();

        if (pathToAnimals.contains(":/")) {
            pathToAnimals = pathToAnimals.replaceFirst("/", "");
        }

        Path path = Paths.get(pathToAnimals);
        File[] files = path.toFile().listFiles();

        if (files != null) {
            for (File f : files) {
                if (isJavaClassFile(f)) {
                    try {

                        class AnimalClassLoader extends ClassLoader {
                            @Override
                            protected Class<?> findClass(String name) throws ClassNotFoundException {
                                try {
                                    byte[] bytes = Files.readAllBytes(f.toPath());
                                    return defineClass(null, bytes, 0, bytes.length);
                                } catch (IOException ex) {
                                    return super.findClass(name);
                                }
                            }
                        }

                        AnimalClassLoader animalClassLoader = new AnimalClassLoader();

                        String className = f.getName().replace(".class", "");
                        Class clazz = animalClassLoader.findClass(className);

                        if (hasPublicConstructor(clazz) && isAnimal(clazz)) {
                            //System.out.println(className + " has default public constructor!");
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

    private static boolean isJavaClassFile(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".class");
    }
}
