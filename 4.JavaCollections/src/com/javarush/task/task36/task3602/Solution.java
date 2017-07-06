package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        //System.out.println(isItThrowsIndexOutOfBoundsException(ArrayList.class));
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz : classes) {
            if (List.class.isAssignableFrom(clazz) && Modifier.isPrivate(clazz.getModifiers()) && Modifier.isStatic(clazz.getModifiers())
                    && isItThrowsIndexOutOfBoundsException(clazz)) {
                return clazz;
            }
        }
        return null;
    }

    private static boolean isItThrowsIndexOutOfBoundsException(Class clazz) {
        try {
            if (List.class.isAssignableFrom(clazz)) {
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor c : constructors) {
                    if (c.getParameterCount() == 0) {
                        c.setAccessible(true);
                        List list = (List)c.newInstance();
                        list.add(new Object());
                        list.get(0);
                        return false;
                    }
                }
            }
        }
        catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
            //System.out.println(e);
            return true;
        } catch (Exception e) {
            //System.out.println(e);
            return false;
        }
        return false;
    }
}
