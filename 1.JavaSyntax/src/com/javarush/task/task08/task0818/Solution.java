package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Kuznetsov", 900);
        map.put("Hitrov", 1000);
        map.put("Ivanov", 100);
        map.put("Petrov", 200);
        map.put("Sidorov", 300);
        map.put("Lisitsin", 800);
        map.put("Zayzev", 400);
        map.put("Volkov", 500);
        map.put("Medvedev", 600);
        map.put("Orlov", 700);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        for (Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() < 500) {
                it.remove();
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();

        //map.forEach((s, integer) -> System.out.println(String.format("%s: %d", s, integer)));

        removeItemFromMap(map);

        //System.out.println("After removing:");
        //map.forEach((s, integer) -> System.out.println(String.format("%s: %d", s, integer)));
    }
}