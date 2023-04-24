package com.gdsc.canigraduate.domain.user.lecture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by im2sh
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLectureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "LECTURE_ID")
    private UserLecture userLecture;

    @Enumerated(EnumType.STRING)
    private UserLectureType userLectureType;

    private Integer lectureYear; // 연도
    private String semester; // 학기
    private String major; // 교과목 구분
    private String code; // 교과목코드
    private String lectureName; //교과목명
    private Integer credit; // 학점
    private String grade; //성적등급
    private Double score; //점수

    @Builder
    public UserLectureDetail(Integer lectureYear, String semester, String major, String code, String lectureName, Integer credit, String grade, Double score, UserLecture userLecture) {
        this.lectureYear = lectureYear;
        this.semester = semester;
        this.major = major;
        this.code = code;
        this.lectureName = lectureName;
        this.credit = credit;
        this.grade = grade;
        this.score = score;
        this.userLecture = userLecture;
    }


    public void setType(String major) {
        if (major.equals("전공") || major.equals("전공필수"))
            this.userLectureType = UserLectureType.전공;
        else if (major.equals("교양") || major.equals("기본소양"))
            this.userLectureType = UserLectureType.교양;
        else if (major.equals("공학전공"))
            this.userLectureType = UserLectureType.공학전공;
        else if(major.equals("전공기반"))
            this.userLectureType = UserLectureType.전공기반;
        else
            this.userLectureType = UserLectureType.일반선택;
    }

    public void setUserLecture(UserLecture userLecture) {
        this.userLecture = userLecture;
    }

    public String YearToString(Integer year, String semester) {
        return year.toString() + " " + semester;
    }
}
