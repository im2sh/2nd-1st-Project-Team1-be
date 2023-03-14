package com.gdsc.crawler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.regex.Pattern;

public class CrawlerApplication {

    static String regex = "^(19|20)\\d{2}$";

    public static void main(String[] args) {
        if (args.length != 1 || !Pattern.matches(regex, args[0])) {
            System.out.println("올바른 연도를 입력해주세요.");
            System.exit(1);
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--remote-allow-origins=*");

        Crawler crawler = new Crawler(opts, 5);
        List<Lecture> lectures = crawler.getLectures(args[0]);

        for (Lecture l : lectures) {
            System.out.println("l = " + l);
        }
    }
}
