package com.gdsc.canigraduate.dto.userLecture;

import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import lombok.Builder;
import lombok.Data;

@Data
public class UserLectureDetailDTO {
    private Integer year; // 연도
    private String semester; // 학기
    private String major; // 교과목 구분
    private String code; // 교과목코드
    private String lectureName; //교과목명
    private Integer credit; // 학점
    private String grade; //성적등급
    private Double score; //점수

    @Builder
    public UserLectureDetailDTO(Integer year, String semester, String major, String code, String lectureName, Integer credit, String grade, Double score, UserLecture userLecture) {
        this.year = year;
        this.semester = semester;
        this.major = major;
        this.code = code;
        this.lectureName = lectureName;
        this.credit = credit;
        this.grade = grade;
        this.score = score;
    }

    public UserLectureDetail toEntity(UserLectureDetailDTO data) {
        UserLectureDetail userLectureDetail = UserLectureDetail.builder()
                .lectureYear(year)
                .semester(semester)
                .major(major)
                .code(code)
                .lectureName(lectureName)
                .credit(credit)
                .grade(grade)
                .score(score).build();
        return userLectureDetail;
    }

    public String YearToString(Integer year, String semester) {
        return year.toString() + " " + semester;
    }
}
