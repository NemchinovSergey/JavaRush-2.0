package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {

    /** Последнее значение идентификатора,
     * которое было использовано при добавлении новой строки в хранилище */
    private Long lastId = 0L;

    /** Стратегия хранения данных */
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    /** Возвращает идентификатор (Long) для заданной строки */
    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        }
        else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    /** Возвращает строку для заданного идентификатора или null,
     * если передан неверный идентификатор */
    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
