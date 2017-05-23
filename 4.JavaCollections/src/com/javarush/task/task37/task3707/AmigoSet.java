package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private final static Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max((int)(collection.size() / 0.75f + 1), 16);
        this.map = new HashMap<>(capacity);
        addAll(collection);
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public AmigoSet<E> clone() throws CloneNotSupportedException {
        try {
            HashMap<E, Object> result = (HashMap<E, Object>) map.clone();
            return new AmigoSet<>(result.keySet());
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }

    @Override
    public boolean add(E e) {
        if (map.containsKey(e)) return false;
        map.put(e, PRESENT);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (map.containsKey(o)) {
            map.remove(o);
            return true;
        }
        return false;
    }
}
