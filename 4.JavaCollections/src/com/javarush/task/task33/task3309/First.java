package com.javarush.task.task33.task3309;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "first")
public class First {
    @XmlElement(name = "second")
    public String item1 = "some string";
    @XmlElement(name = "second")
    public String item2 = "need CDATA because of <second>";
    @XmlElement(name = "second")
    public String item3 = "";
    @XmlElement(name = "third")
    public String item4;
    @XmlElement(name = "forth")
    public Second[] third = new Second[]{new Second()};
    @XmlElement(name = "fifth")
    public String item5 = "need CDATA because of \"";
}

class Second {
    @XmlElement(name = "second")
    public String item1 = "some string";
    @XmlElement(name = "second")
    public String item2 = "need CDATA because of <second>";
}
