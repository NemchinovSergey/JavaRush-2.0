package com.javarush.task.task33.task3309;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "anExample")
@XmlRootElement
public class AnExample {
    public String[] needCDATA = new String[]{"need CDATA because of < and >", "", "ooo"};
    public String st = "sdf";
    public String st2 = "sdf";
    public int ist = 2;
    public int ist2 = 23523;
    public int ist5 = 23;
}
