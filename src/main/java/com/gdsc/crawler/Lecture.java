package com.gdsc.crawler;

import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@Builder
@Getter
public class Lecture {
    String no;
    String year;            // 개설연도
    String semester;        // 개설학기
    String grade;           // 학년
    String lectureType;     // 교과구분
    String university;      // 개설대학
    String major;           // 개설학과
    String lectureNumber;   // 강좌번호
    String lectureName;      // 교과목명
    String credit;          // 학점
    String professor;       // 담당교수

    @Override
    public String toString() {
        return (this.no + " " + this.year + " " + this.semester + " " + this.grade + " " + this.lectureType + " " + this.university + " " + this.major + " " +
                this.lectureNumber + " " + this.lectureName + " " + this.credit + " " + this.professor);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lecture other) {
            return other.no.equals(this.no)
                    && other.year.equals(this.year)
                    && other.semester.equals(this.semester)
                    && other.grade.equals(this.grade)
                    && other.lectureType.equals(this.lectureType)
                    && other.university.equals(this.university)
                    && other.lectureNumber.equals(this.lectureNumber)
                    && other.lectureName.equals(this.lectureName)
                    && other.credit.equals(this.credit)
                    && other.professor.equals(this.professor);
        }
        return false;
    }

    static Lecture fromTr(WebElement tr) {
        List<WebElement> tds = tr.findElements(By.tagName("td")).size() == 0 ?
                tr.findElements(By.tagName("th")) : tr.findElements(By.tagName("td"));
        return new LectureBuilder()
                .no(tds.get(0).getText())
                .year(tds.get(1).getText())
                .semester(tds.get(2).getText())
                .grade(tds.get(3).getText())
                .lectureType(tds.get(4).getText())
                .university(tds.get(5).getText())
                .major(tds.get(6).getText())
                .lectureNumber(tds.get(7).getText())
                .lectureName(tds.get(8).getText())
                .credit(tds.get(9).getText())
                .professor(tds.get(12).getText())
                .build();
    }
}
