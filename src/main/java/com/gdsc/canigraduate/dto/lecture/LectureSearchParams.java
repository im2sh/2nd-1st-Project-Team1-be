package com.gdsc.canigraduate.dto.lecture;

import com.gdsc.canigraduate.domain.lecture.LectureCredit;
import com.gdsc.canigraduate.domain.lecture.LectureGrade;
import com.gdsc.canigraduate.domain.lecture.LectureSemester;
import com.gdsc.canigraduate.domain.lecture.LectureType;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;

@Data
@ParameterObject
public class LectureSearchParams {

    @Parameter(description = "강의 이름")
    private String name;
    @Parameter(description = "강의 코드")
    private String code;
    @Parameter(description = "강의 연도")
    private Integer lectureYear;
    @Parameter(description = "강의 학점")
    private LectureCredit credit;
    @Parameter(description = "강의 학년")
    private LectureGrade grade;
    @Parameter(description = "강의 학기")
    private LectureSemester semester;
    @Parameter(description = "강의 타입")
    private LectureType type;
    @Parameter(description = "필수")
    private Boolean required;
    @Parameter(description = "디자인")
    private Boolean design;
//
//
//    public void setName(String name) {
//        if (name == null || name.isBlank()) this.name = null;
//        else this.name = name;
//    }
//
//    public void setCode(String code) {
//        if (code == null || code.isBlank()) this.code = null;
//        else this.code = code;
//
//    }
//
//    public void setLectureYear(Integer year) {
//        this.lectureYear = year;
//    }
//
//    public void setCredit(String credit) {
//        if (credit == null || credit.isBlank()) this.credit = null;
//            // TODO( 생각해보기, 여기서 Try-Catch 를 사용해야할까? )
//        else this.credit = LectureCredit.fromValue(credit);
//    }
//
//    public void setGrade(String grade) {
//        if (grade == null || grade.isBlank()) this.grade = null;
//        else this.grade = LectureGrade.fromValue(grade);
//    }
//
//    public void setSemester(String semester) {
//        if (semester == null || semester.isBlank()) this.semester = null;
//        else this.semester = LectureSemester.fromValue(semester);
//    }
//
//    public void setType(String type) {
//        if (type == null || type.isBlank()) this.type = null;
//        else this.type = LectureType.fromValue(type);
//    }
//
//    public void setRequired(Boolean required) {
//        this.required = required;
//    }
//
//    public void setDesign(Boolean design) {
//        this.design = design;
//    }
}
