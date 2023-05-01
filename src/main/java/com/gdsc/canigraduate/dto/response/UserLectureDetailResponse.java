package com.gdsc.canigraduate.dto.response;


import lombok.Data;

@Data
public class UserLectureDetailResponse {
    private Integer year; // 연도
    private String semester; // 학기
    private String major; // 교과목 구분
    private String code; // 교과목코드
    private String lectureName; //교과목명
    private Integer credit; // 학점
    private String grade; //성적등급
    private Double score; //점수

    public UserLectureDetailResponse(Integer year, String semester, String major, String code, String lectureName, Integer credit, String grade, Double score) {
        this.year = year;
        this.semester = semester;
        this.major = major;
        this.code = code;
        this.lectureName = lectureName;
        this.credit = credit;
        this.grade = grade;
        this.score = score;
    }
}
