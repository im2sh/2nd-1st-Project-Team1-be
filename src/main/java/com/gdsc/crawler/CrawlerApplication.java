package com.gdsc.crawler;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.regex.Pattern;

public class CrawlerApplication {

    static String regex = "^(19|20)\\d{2}$";

    public static void main(String[] args) {
        if (args.length != 4 || !Pattern.matches(regex, args[3])) {
            System.out.println("사용법) [검색옵션1] [검색옵션2] [검색옵션3] [연도] ex) 대학 IT대학 컴퓨터학부 2017");
            System.exit(1);
        }

        WebDriverManager.chromedriver().setup();

        Crawler crawler = new Crawler(5);
        List<Lecture> lectures = crawler.getLectures(args[0], args[1], args[2], args[3]);

        for (Lecture l : lectures) {
            System.out.println("l = " + l);
        }
    }
}
