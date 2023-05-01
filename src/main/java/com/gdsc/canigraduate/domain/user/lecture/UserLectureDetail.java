package com.gdsc.canigraduate.domain.user.lecture;

import com.gdsc.canigraduate.dto.response.UserLectureDetailResponse;
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

    public UserLectureDetailResponse toResponse(){
        Integer lectureYear = getLectureYear(); // 연도
        String semester =getSemester();
        String major = getMajor(); // 교과목 구분
        String code = getCode();// 교과목코드
        String lectureName = getLectureName(); //교과목명
        Integer credit = getCredit(); // 학점
        String grade = getGrade(); //성적등급
        Double score = getScore(); //점수

        return new UserLectureDetailResponse(lectureYear, semester, major, code, lectureName, credit, grade, score);
    }
}
