package com.gdsc.canigraduate.dto.excel;

import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import lombok.Data;

/**
 * Created by im2sh
 */

@Data
public class ExcelData {
    private Integer year; // 연도
    private String semester; // 학기
    private String major; // 교과목 구분
    private String code; // 교과목코드
    private String lectureName; //교과목명
    private Integer credit; // 학점
    private String grade; //성적등급
    private Double score; //점수

    public UserLectureDetailDTO toDetailDTO(ExcelData data) {
        UserLectureDetailDTO userLectureDetailDTO = UserLectureDetailDTO.builder()
                .year(year)
                .semester(semester)
                .major(major)
                .code(code)
                .lectureName(lectureName)
                .credit(credit)
                .grade(grade)
                .score(score).build();
        return userLectureDetailDTO;
    }
}
