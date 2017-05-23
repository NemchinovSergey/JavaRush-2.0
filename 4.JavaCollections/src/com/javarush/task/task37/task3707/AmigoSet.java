package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");

        out.writeInt(capacity);
        out.writeFloat(loadFactor);

        for (E key : map.keySet()) {
            out.writeObject(key);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < capacity; i++) {
            E key = (E)in.readObject();
            map.put(key, PRESENT);
        }
    }

}
