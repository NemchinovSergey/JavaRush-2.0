package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Новосибирск");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document doc = getDocument();
            Element tempElement = doc.getElementsByClass("template").first();

//            System.out.println(tempElement);

            Element tempNewElement = tempElement.clone();
            tempNewElement.removeAttr("style");
            tempNewElement.removeClass("template");

//            System.out.println("-----------------");
//            System.out.println(tempNewElement);

            //doc.select("tr[class=vacancy]").not("tr[class=vacancy template").remove();
            doc.select("tr[class=vacancy]").remove();

//            System.out.println("-----------------");
//            System.out.println(doc);

            for (Vacancy vacancy : vacancies) {
                Element vacansyElement = tempNewElement.clone();
                vacansyElement.getElementsByClass("city").first().appendText(vacancy.getCity());
                vacansyElement.getElementsByClass("companyName").first().appendText(vacancy.getCompanyName());
                vacansyElement.getElementsByClass("salary").first().appendText(vacancy.getSalary());

                Element refElement = vacansyElement.getElementsByClass("title").first().getElementsByTag("a").first();
                refElement.appendText(vacancy.getTitle());
                refElement.attr("href", vacancy.getUrl());

//                System.out.println("=============");
//                System.out.println(vacansyElement);

                tempElement.before(vacansyElement.outerHtml());

//                System.out.println("-----------------");
//                System.out.println(doc);
            }

            return doc.html();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    protected Document getDocument() throws IOException {
        // for debug from IDE:
        //return Jsoup.parse(Paths.get(filePath).getFileName().toAbsolutePath().toFile(), "UTF-8");

        // for JavaRush validation !!!!!!!!!!!!!!!!!!!!!!! :
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String fileContent) {
        if (fileContent == null || fileContent.length() == 0) {
            return;
        }
        try (
                //////BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(filePath).toAbsolutePath().normalize().toFile()))

                // for debug from IDE:
                //BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(filePath).getFileName().toAbsolutePath().toFile()))

                // for JavaRush validation !!!!!!!!!!!!!!!!!!!!!!! :
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))
        ) {
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
