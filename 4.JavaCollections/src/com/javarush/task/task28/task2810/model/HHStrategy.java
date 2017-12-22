package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**  Парсит http://hh.ru
     https://novosibirsk.hh.ru/search/vacancy?text=java+Новосибирск
     https://hh.ru/search/vacancy?text=java+ADDITIONAL_VALUE&page=PAGE_VALUE */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
    private static final String referrer = "referrer";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        ArrayList<Vacancy> res = new ArrayList<>();
        Document doc;

        try {
            doc = Jsoup.connect(String.format(URL_FORMAT, searchString, 0))
                    .userAgent(userAgent)
                    .referrer(referrer)
                    .get();

            //System.out.println(doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
