package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() {
        File[] files = Paths.get(packageName).toFile().listFiles();
        if (files == null) {
            return;
        }

        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(name));
                    return defineClass(null, bytes, 0, bytes.length);
                } catch (IOException ex) {
                    return super.findClass(name);
                }
            }
        };

        for (File f : files) {
            if (isJavaClassFile(f)) {
                try {
                    Class clazz = myClassLoader.loadClass(f.getAbsolutePath());
                    hiddenClasses.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        try {
            for (Class clazz : hiddenClasses) {
                if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    if (isImplementsHiddenClass(clazz)) {
                        Constructor defaultConstructor = getDefaultConstructor(clazz);
                        if (defaultConstructor != null) {
                            return (HiddenClass) defaultConstructor.newInstance();
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isJavaClassFile(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".class");
    }

    private static Constructor getDefaultConstructor(Class clazz) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            if (c.getParameterCount() == 0) {
                c.setAccessible(true);
                return c;
            }
        }
        return null;
    }

    private static boolean isImplementsHiddenClass(Class clazz) {
        return HiddenClass.class.isAssignableFrom(clazz);
    }

    //region Not used methods
    private static boolean hasPublicConstructor(Class clazz) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            if (c.getParameterCount() == 0 && Modifier.isPublic(c.getModifiers())) {
                return true;
            }
        }
        return false;
    }

    private static String getClassName(File file) {
        return file.getName().replace(".class", "");
    }
    //endregion
}

