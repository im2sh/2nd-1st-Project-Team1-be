/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.Department;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Lecture {

    private String name;

    private String code;

    private Integer lectureYear;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private LectureType type;

    @Enumerated(EnumType.STRING)
    private LectureGrade grade;

    @Enumerated(EnumType.STRING)
    private LectureSemester semester;

    @Enumerated(EnumType.STRING)
    private LectureCredit credit;

    private Boolean required;

    private Boolean design;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public Lecture(
            String name,
            Integer lectureYear,
            String code,
            Department department,
            LectureType type,
            LectureGrade grade,
            LectureSemester semester,
            LectureCredit credit,
            boolean required,
            boolean design
    ) {
        this.name = name;
        this.lectureYear = lectureYear;
        this.code = code;
        this.department = department;
        this.type = type;
        this.grade = grade;
        this.semester = semester;
        this.credit = credit;
        this.required = required;
        this.design = design;
    }
}
