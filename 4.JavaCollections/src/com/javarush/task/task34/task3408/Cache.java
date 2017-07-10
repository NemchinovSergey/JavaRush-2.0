package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V value = cache.get(key);
        if (value == null) {
            Constructor constructor = clazz.getConstructor(key.getClass());
            value = (V)constructor.newInstance(key);
            cache.put(key, value);
        }
        return value;
    }

    public boolean put(V obj)  {
        try {
            Method getKeyMethod = obj.getClass().getDeclaredMethod("getKey");
            getKeyMethod.setAccessible(true);
            K key = (K) getKeyMethod.invoke(obj);
            cache.put(key, obj);
            return cache.containsKey(key);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
