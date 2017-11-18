package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T>{
        // создаём реальный итератор
        Iterator<T> iterator = Solution.super.iterator();

        @Override
        public boolean hasNext() {
            // Если список не пустой, то у нашего кругового итератора
            // всегда есть следующий элемент
            return size() > 0;
        }

        @Override
        public T next() {
            // если список не пустой
            if (hasNext()) {
                // и есть следующий элемент, то возращаем его
                if (iterator.hasNext()) {
                    return iterator.next();
                }
                else {
                    // если дошли до конца, то создаём новый итератор
                    iterator = Solution.super.iterator();
                    // и возвращаем первый элемент
                    return iterator.next();
                }
            }
            else
                return null; // список пустой - возвращаем null
        }

        @Override
        public void remove() {
            iterator.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            iterator.forEachRemaining(action);
        }
    }
}
