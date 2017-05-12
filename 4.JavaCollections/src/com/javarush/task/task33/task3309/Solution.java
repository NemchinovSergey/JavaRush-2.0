package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/*
Комментарий внутри xml

http://help.javarush.ru/questions/178228/com-javarush-task-task33-task3309-%D1%85%D0%B8%D0%BD%D1%82%D1%8B-%D0%B8-%D0%BF%D0%BE%D0%B4%D1%81%D0%BA%D0%B0%D0%B7%D0%BA%D0%B8
Для тех кто придет сюда за помощью и пониманием:

- Сериализация ojb в xml может содержать CDATA - сериализация будет содержать CDATA, только в том случае если вы сами ее туда "запихнете". Средствами jaxb этого сделать нельзя. Наиболее простой способ это сделать - использовать библиотеку org.w3c.dom (присутствует в java c JDK1.4).
- Вывод из п.1 - маршалить надо не в StringWriter, a в DOM документ.
- В DOM документе есть Node-ы, вот с ними в этой задаче и предстоит работать (заменить текстовые на CDATA, где есть escape символы (их в xml всего 5 - <>'"&) и добавить комментарии. ПАРСИТЬ ничего не надо!!! просто работайте с Node-ами. На этом этапе вас интересуют Node.TEXT_NODE, Node.CDATA_SECTION_NODE и Node.COMMENT_NODE.
- Поиск текстовых Node с escape символами можно осуществить при помощи простого regex:
".*[<>&'"].*"
- После того как вы все нашли, заэскейпили и добавили комментарии, нужно как-то все это "слить" в XML - в помощь вам javax.xml.transform (since JDK 1.5).
- В конце получившийся XML документ нужно превратить в строку с помощью StringWriter-a.

P.S.:
- standalone для xml документа выставляется в DOM документе, а charset - в трансформере.
- метод main не участвует в проверке валидатором.

P.P.S:
- небольшая помощь в виде ссылки
https://examples.javacodegeeks.com/core-java/xml/dom/add-comment-to-dom-document/

*/


public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        return null;
    }

    public static void main(String[] args) throws JAXBException {

    }
}
