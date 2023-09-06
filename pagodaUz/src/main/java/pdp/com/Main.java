package pdp.com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static Document getPage() throws IOException {
        String url = "";

        System.out.println("""
                1.Ташкент              9.Зарафшан
                2.Андижан             10.Карши
                3.Бухара              11.Навои
                4.Гулистан            12.Наманган
                5.Джизак              13.Хива
                6.Нукус               14.Термез
                7.Самарканд           15.Ургенч
                8.Фергана
                """);

        System.out.print("Выберите регион: ");
        Scanner scanner = new Scanner(System.in);
        int location = scanner.nextInt();
        switch (location){
            case 1 -> url = "https://pogoda.uz/tashkent";
            case 2 -> url = "https://pogoda.uz/andijan";
            case 3 -> url = "https://pogoda.uz/bukhara";
            case 4 -> url = "https://pogoda.uz/gulistan";
            case 5 -> url = "https://pogoda.uz/jizzakh";
            case 6 -> url = "https://pogoda.uz/nukus";
            case 7 -> url = "https://pogoda.uz/samarkand";
            case 8 -> url = "https://pogoda.uz/ferghana";
            case 9 -> url = "https://pogoda.uz/zarafshan";
            case 10 -> url = "https://pogoda.uz/karshi";
            case 11 -> url = "https://pogoda.uz/navoi";
            case 12 -> url = "https://pogoda.uz/namangan";
            case 13 -> url = "https://pogoda.uz/khiva";
            case 14 -> url = "https://pogoda.uz/termez";
            case 15 -> url = "https://pogoda.uz/urgench";
        }

        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static void week(Document page){
        Elements weekTable = page.select("td[class=weather-row-day]");
        Elements tempTable = page.select("td[class=weather-row-forecast]");
        Elements opsTable = page.select("td[class=weather-row-desc]");
        Elements osodkaTable = page.select("td[class=weather-row-pop]");

        System.out.println();
        System.out.println("Прогноз на неделю");

        System.out.println("День \t \t \t Температура \t Описание \t Осадки" );
        for (int i = 0; i < 5; i++) {
            System.out.println(weekTable.get(i).text() + "\t" + tempTable.get(i).text()
                    + "\t" + opsTable.get(i).text() +"\t"+ osodkaTable.get(i).text());
        }


    }

    public static void main(String[] args) throws IOException {
        Document page = getPage();

        Element tableWth = page.select("div[class=padd-block]").first();
        Element tableDay = page.select("div[class=current-day]").first();
        Element tempratura  = page.select("div[class=current-forecast]").first();
        Element opisanya = page.select("div[class=current-forecast-desc]").first();

        Elements col1 = page.select("div[class=col-1]");
        Elements col2 = page.select("div[class=col-2]");

        String name = tableWth.text().split(" ")[0];


        System.err.println("\n" +name);
        System.out.println(tableDay.text());
        System.out.println(tempratura.text() + "\t" + opisanya.text());
        System.out.println(col1.text());
        System.out.println(col2.text());

        week(page);


    }
}