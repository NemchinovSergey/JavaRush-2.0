package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        final int[] size = {0};
        map.values().forEach(vs -> size[0] += vs.size());
        return size[0];
    }

    @Override
    public V put(K key, V value) {
        List<V> list;

        if (containsKey(key)) {
            // Получаем список по ключу
            list = map.get(key);
            if (list == null) {
                throw new IllegalStateException();
            }
            V prev = null;
            if (list.size() > 0) {
                prev = list.get(list.size() - 1);
            }
            if (list.size() >= repeatCount) {
                list.remove(0);
            }
            list.add(value);
            return prev;
        }
        else {
            // Если списка нет, то создаём его и добавляем список в мапу
            list = new ArrayList<>(repeatCount);
            list.add(value);
            map.put(key, list);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        if (!map.containsKey(key)) {
            return null;
        }

        V prev = null;
        List<V> list = map.get(key);
        if (list != null) {
            if (list.size() > 0) {
                prev = list.remove(0);
            }
            if (list.size() == 0) {
                map.remove(key);
            }
            return prev;
        }
        else {
            return null;
        }
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        List<V> list = new ArrayList<>();
        map.values().forEach(list::addAll);
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}