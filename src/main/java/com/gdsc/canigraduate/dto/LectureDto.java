package com.gdsc.canigraduate.dto;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LectureDto {

    private String semester;
    private Integer majorCredit;
    private Integer cultureCredit;
    private Integer normalCredit;

    public LectureDto(LectureDto lectureDto){
        semester = lectureDto.semester;
        majorCredit = lectureDto.majorCredit;
        cultureCredit = lectureDto.cultureCredit;
        normalCredit = lectureDto.normalCredit;
    }

    @Builder
    public LectureDto(String semester, Integer majorCredit, Integer cultureCredit, Integer normalCredit){
        this.semester = semester;
        this.majorCredit = majorCredit;
        this.cultureCredit = cultureCredit;
        this.normalCredit = normalCredit;
    }

    public Lecture toEntity(){
        Lecture lecture = Lecture.builder()
                .semester(semester)
                .majorCredit(majorCredit)
                .cultureCredit(cultureCredit)
                .normalCredit(normalCredit).build();
        return lecture;
    }
}
