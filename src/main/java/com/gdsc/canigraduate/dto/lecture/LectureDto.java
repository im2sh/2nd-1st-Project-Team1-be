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
    private String type;
    private String year;
    private String semester;
    private String credit;
    private Boolean required;
    private Boolean design;

    public LectureDto(Lecture lecture) {
        this.name = lecture.getName();
        this.code = lecture.getCode();
        this.type = lecture.getType().getValue();
        this.year = lecture.getGrade().getValue();
        this.semester = lecture.getSemester().getValue();
        this.credit = lecture.getCredit().getValue();
        this.required = lecture.getRequired();
        this.design = lecture.getDesign();
    }
}
