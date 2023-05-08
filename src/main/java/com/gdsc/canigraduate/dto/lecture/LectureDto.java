/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.dto.lecture;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import lombok.Data;

@Data
public class LectureDto {

    private String name;
    private String code;
    private Integer lectureYear;
    private String credit;
    private String grade;
    private String semester;
    private String type;
    private Boolean required;
    private Boolean design;

    public LectureDto(Lecture lecture) {
        this.name = lecture.getName();
        this.code = lecture.getCode();
        this.lectureYear = lecture.getLectureYear();
        this.credit = lecture.getCredit().getValue();
        this.grade = lecture.getGrade().getValue();
        this.semester = lecture.getSemester().getValue();
        this.type = lecture.getType().getValue();
        this.required = lecture.getRequired();
        this.design = lecture.getDesign();
    }
}
