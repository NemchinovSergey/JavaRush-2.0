package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements List<String>, Serializable {
    private int size = 0;
    private Entry<String> root = null;

    private Entry<String> first;
    private Entry<String> current;
    private Entry<String> last;

    public CustomTree() {
    }

    public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        System.out.println(list);
        ((CustomTree) list).remove("2");
        ((CustomTree) list).remove("9");
        System.out.println("Удалили 2 и 9:");
        System.out.println(list);
        for (int i = 16; i < 21; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Добавили 16,17,18,19,20");
        System.out.println(list);
        ((CustomTree) list).remove("18");
        ((CustomTree) list).remove("20");
        System.out.println("Удалили 18 и 20:");
        System.out.println(list);
        list.add("21");
        list.add("22");
        System.out.println("Добавили 21 и 22:");
        System.out.println(list);
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("1"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("2"));
        System.out.println("Expected 1, actual is " + ((CustomTree) list).getParent("3"));
        System.out.println("Expected 1, actual is " + ((CustomTree) list).getParent("4"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("5"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("6"));
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("7"));
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("9"));
        System.out.println("Expected 4, actual is " + ((CustomTree) list).getParent("10"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("12"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("13"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("14"));
        System.out.println("Expected 7, actual is " + ((CustomTree) list).getParent("15"));
        System.out.println("Expected 7, actual is " + ((CustomTree) list).getParent("16"));
        System.out.println("Expected 8, actual is " + ((CustomTree) list).getParent("17"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("18"));
        System.out.println("Expected 10, actual is " + ((CustomTree) list).getParent("19"));
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("20"));
        System.out.println("Expected 10, actual is " + ((CustomTree) list).getParent("21"));
        System.out.println("Expected 15, actual is " + ((CustomTree) list).getParent("22"));
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Entry<String> x = first; x != null; x = x.next) {
                if (x.elementName == null) {
                    unlink(x);
                }
            }
        }
        for (Entry<String> x = first; x != null; x = x.next) {
            if (x.elementName.equals(o)) {
                unlink(x);
            }
        }
        return false;
    }

    void unlink(Entry<String> x) {
        x.elementName = null;

        if (x.leftChild != null) unlink(x.leftChild);
        if (x.rightChild != null) unlink(x.rightChild);

        if (x.parent != null) {
            if (x.parent.leftChild == x) x.parent.leftChild = null;
            else if (x.parent.rightChild == x) x.parent.rightChild = null;
        }

        if (x == current) {
            current = x.next;
        }

        if (x == last) {
            last = x.prev;
            if (x.prev != null) x.prev.next = null;
        }
        if (x == first) {
            first = x.next;
            if (x.next != null) x.next.prev = null;
        }

        if (x.prev != null && x.next != null) {
            x.next.prev = x.prev;
            x.prev.next = x.next;
        }
        size--;
        modCount++;
    }

    @Override
    public boolean add(String s) {
        linkLast(s);
        return true;
    }

    void linkLast(String s) {
        Entry<String> newOne;
        if (current == null) {
            newOne = new Entry<String>(s, root);
            if (first == null) {
                first = newOne;
            } else {
                first.next = newOne;
                newOne.prev = first;
                current = first;
            }
        } else {
            newOne = new Entry<String>(s, current);
            if (current.leftChild == null) current.leftChild = newOne;
            else if (current.rightChild == null) {
                current.rightChild = newOne;
            } else {
                current = current.next;
                newOne.parent = current;
                current.leftChild = newOne;
            }
            last.next = newOne;
            newOne.prev = last;
        }
        last = newOne;
        size++;
        modCount++;
    }

    @Override
    public void clear() {
        for (Entry<String> x = first; x != null; ) {
            Entry<String> next = x.next;
            x.elementName = null;
            x.parent = null;
            x.prev = null;
            x.next = null;
            x.leftChild = null;
            x.rightChild = null;
            x = next;
        }
        first = null;
        last = null;
        current = null;
        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String elementName) {
        if (elementName == null) {
            for (Entry<String> x = first; x != null; x = x.next) {
                if (x.elementName == null) {
                    if (x.parent != null) return x.parent.elementName;
                }
            }
        } else {
            for (Entry<String> x = first; x != null; x = x.next) {
                if (elementName.equals(x.elementName)) {
                    if (x.parent != null) return x.parent.elementName;
                }
            }
        }
        return null;
    }

    private class Itr implements Iterator<String> {

        Entry<String> cursor = first;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public String next() {
            Entry<String> cur = cursor;
            cursor = cur.next;
            return cur.elementName;
        }

        @Override
        public void remove() {
            if (cursor != null) {
                Entry<String> cur = cursor;
                cursor = cur.next;
                CustomTree.this.remove(cur.elementName);
            }
        }
    }

    private class Entry<T> implements Serializable {
        T elementName;

        Entry<T> parent;

        Entry<T> leftChild;
        Entry<T> rightChild;

        Entry<T> next;
        Entry<T> prev;

        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;

        public Entry(T elementName, Entry<T> parent) {
            this.elementName = elementName;
            this.parent = parent;
        }

        public Entry(T elementName) {
            this.elementName = elementName;
        }

        public void checkChildren() {
            availableToAddLeftChildren = leftChild == null;
            availableToAddRightChildren = rightChild == null;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}