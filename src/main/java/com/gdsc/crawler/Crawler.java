package com.gdsc.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.core.io.NumberInput.parseInt;

public class Crawler extends ChromeDriver {
    public Crawler(long duration) {
        super(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        this.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
        this.manage().window().maximize();
    }

    public List<Lecture> getLectures(String catgegory, String university, String major, String year) {
        this.get("https://sy.knu.ac.kr");

        this.setEstblYearTo(year);
        this.setSearchOption(catgegory, university, major);

        List<Lecture> lectures = this.crawl();

        this.quit();

        return lectures;
    }

    private void setEstblYearTo(String year) {
        WebElement estblYear = this.findElement(By.id("schEstblYear___input"));
        estblYear.clear();
        estblYear.sendKeys(year);
    }

    private void setSearchOption(String catgegory, String university, String major) {
        this.findElement(By.id("wq_uuid_67")).click();

        new Select(this.findElement(By.id("schSbjetCd1"))).selectByVisibleText(catgegory);
        new Select(this.findElement(By.id("schSbjetCd2"))).selectByVisibleText(university);
        new Select(this.findElement(By.id("schSbjetCd3"))).selectByVisibleText(major);

        this.findElement(By.id("btnSearch")).click();
    }

    private List<Lecture> crawl() {
        ArrayList<Lecture> lectures = new ArrayList<>();

        WebElement thead = this.findElement(By.id("grid01_head_table"));
        WebElement tbody = this.findElement(By.id("grid01_body_tbody"));

        // head 추가
        lectures.add(Lecture.fromTr(thead.findElement(By.tagName("tr"))));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 첫 body 추가
        tbody.findElements(By.tagName("tr")).subList(0, 18)
                .forEach(e -> lectures.add(Lecture.fromTr(e)));

        String numOfLecture = this.findElement(By.id("wq_uuid_78_lblCount")).getText();

        // 18개가 넘으면 테이블을 내리며 크롤링
        if (parseInt(numOfLecture) >= 18) {
            tbody.findElements(By.tagName("tr")).get(17).click();
            Lecture lecture;
            do {
                this.tableDown();
                lecture = Lecture.fromTr(tbody.findElements(By.tagName("tr")).get(17));
                lectures.add(lecture);
            } while (!lecture.no.equals(numOfLecture));
        }

        return lectures;
    }

    private void tableDown() {
        new Actions(this).sendKeys(Keys.DOWN).perform();
    }
}
