package com.gdsc.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Crawler extends ChromeDriver {

    public Crawler(ChromeOptions chromeOptions, long duration) {
        super(chromeOptions);
        this.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
        this.manage().window().maximize();
    }

    public void start(String year) {
        this.get("https://sy.knu.ac.kr");

        this.setEstblYearTo(year);
        this.setSearchOption();

        this.getLecture();

        this.quit();
    }

    private void setEstblYearTo(String year) {
        WebElement estblYear = this.findElement(By.id("schEstblYear___input"));
        estblYear.clear();
        estblYear.sendKeys(year);
    }

    private void setSearchOption() {
        this.findElement(By.id("wq_uuid_67")).click();

        new Select(this.findElement(By.id("schSbjetCd1"))).selectByVisibleText("대학");
        new Select(this.findElement(By.id("schSbjetCd2"))).selectByVisibleText("IT대학");
        new Select(this.findElement(By.id("schSbjetCd3"))).selectByVisibleText("컴퓨터학부");

        this.findElement(By.id("btnSearch")).click();
    }

    private void getLecture() {
        WebElement thead = this.findElement(By.id("grid01_head_table"));
        WebElement tbody = this.findElement(By.id("grid01_body_tbody"));

        try (FileWriter fw = new FileWriter("file.txt")) {
            fw.write(Lecture.fromTr(thead.findElement(By.tagName("tr"))).toString());
            fw.write('\n');

            Thread.sleep(500);

            tbody.findElements(By.tagName("tr"))
                    .forEach((webElement -> {
                        try {
                            if (!webElement.getText().trim().equals("")) {
                                fw.write(Lecture.fromTr(webElement).toString());
                                fw.write('\n');
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
            int numOfLecture = Integer.parseInt(this.findElement(By.id("wq_uuid_78_lblCount")).getText());
            System.out.println("numOfLecture = " + numOfLecture);

            WebElement item = tbody.findElements(By.tagName("tr")).get(17);
            item.click();
            this.tableDown();
            item = tbody.findElements(By.tagName("tr")).get(17);

            while (Integer.parseInt(Lecture.fromTr(item).no) != numOfLecture) {
                fw.write(Lecture.fromTr(item).toString());
                fw.write('\n');
                this.tableDown();
                item = tbody.findElements(By.tagName("tr")).get(17);
            }

            item = tbody.findElements(By.tagName("tr")).get(17);
            fw.write(Lecture.fromTr(item).toString());
            fw.write('\n');
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void tableDown() {
        new Actions(this).sendKeys(Keys.DOWN).perform();
    }
}
