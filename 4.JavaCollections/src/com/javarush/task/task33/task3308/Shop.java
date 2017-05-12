package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(name = "shop")
public class Shop {
    public Goods goods = new Goods();
    public int count = 12;
    public double profit = 123.4;
    public String[] secretData;

    public Shop() {
        goods.add("S1");
        goods.add("S2");
        secretData = new String[] {"String1", "String2", "String3", "String4", "String5" };
    }

    @XmlRootElement
    @XmlType(name = "goods")
    public static class Goods {
        public List<String> names = new ArrayList<>();

        void add(String name) {
            names.add(name);
        }
    }
}
