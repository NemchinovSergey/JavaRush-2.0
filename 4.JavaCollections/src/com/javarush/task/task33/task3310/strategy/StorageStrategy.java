package com.javarush.task.task33.task3310.strategy;

public interface StorageStrategy {

    /** Должен вернуть true, если хранилище содержит переданный ключ */
    boolean containsKey(Long key);

    /** Должен вернуть true, если хранилище содержит переданное значение */
    boolean containsValue(String value);

    /** Добавляет в хранилище новую пару ключ – значение */
    void put(Long key, String value);

    /** Возвращает ключ для переданного значения */
    Long getKey(String value);

    /** Возвращает значение для переданного ключа */
    String getValue(Long key);
}
