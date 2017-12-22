package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсит http://hh.ru
 * https://novosibirsk.hh.ru/search/vacancy?text=java+Новосибирск
 * https://hh.ru/search/vacancy?text=java+ADDITIONAL_VALUE&page=PAGE_VALUE
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
    private static final String referrer = "referrer";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        ArrayList<Vacancy> res = new ArrayList<>();
        int page = 0;

        while (true) {
            try {
                Document doc = getDocument(searchString, page);
                Elements elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) {
                    break;
                }
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").get(0).text());
                    String salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();
                    vacancy.setSalary((salary != null) ? salary : "");
                    vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").get(0).text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").get(0).text());
                    vacancy.setSiteName("http://hh.ru");
                    vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").get(0).attr("href"));
                    //vacancy.setResponsibility(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_responsibility").get(0).text());
                    //vacancy.setRequirement(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy_snippet_requirement").get(0).text());
                    res.add(vacancy);
                }
                page++;
            } catch (IOException ignore) {
            }
        }
        return res;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = null;
        doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(userAgent)
                .referrer(referrer)
                //.timeout(timeout)
                .get();
        //System.out.println(doc.html());

        return doc;
    }
}
