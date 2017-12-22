package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//https://moikrug.ru/vacancies?q=java+Novosibirsk&page=1
public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data2.html";    //fake page for debug

    //private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
    private static final String referrer = "referrer";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        ArrayList<Vacancy> res = new ArrayList<>();
        String siteName = "https://moikrug.ru";
        int page = 0;    // JavaRush требует начальную страницу 0, но у сайта реально начальный номер = 1
        while (true) {
            try {
                Document doc = getDocument(searchString, page);
                Elements elements = doc.getElementsByClass("job");
                if (elements.size() == 0) {
                    break;
                }
                for (Element element : elements) {
//                    System.out.println(element.toString());
//                    System.out.println("---------");

                    Vacancy vacancy = new Vacancy();

                    Element titleHrefElement = element.getElementsByClass("title").first().getElementsByAttribute("href").first();
                    vacancy.setTitle(titleHrefElement.text());
                    vacancy.setUrl(siteName + titleHrefElement.attr("href"));

                    Element companyHrefElement = element.getElementsByClass("company_name").first();  //.getElementsByAttribute("href").first();
                    vacancy.setCompanyName(companyHrefElement.text());  //(siteName + companyHrefElement.attr("href"));

                    Element salaryElement = element.getElementsByClass("salary").first().getElementsByClass("count").first();
                    vacancy.setSalary((salaryElement != null) ? salaryElement.text() : "");

                    Element cityElement = element.getElementsByClass("location").first();
                    Element cityTitleElement = null;
                    if (cityElement != null) {
                        cityTitleElement = cityElement.getElementsByAttribute("href").first();
                    }
                    vacancy.setCity((cityTitleElement != null) ? cityTitleElement.text() : "");

                    vacancy.setSiteName(siteName);

                    //vacancy.setResponsibility();
                    //vacancy.setRequirement();
                    res.add(vacancy);

                }
                page++;

                // for debug from IDE with fake page:
                if ("http://javarush.ru/testdata/big28data2.html".equals(URL_FORMAT)) {
                    break;
                }

            } catch (IOException ignore) {
            }
        }
        return res;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(userAgent)
                .referrer(referrer)
                //.timeout(timeout)
                .get();
        //System.out.println(doc.html());
        return doc;
    }
}
