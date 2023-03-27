package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;

public enum LectureGrade implements BaseEnum {
    FIRST("1학년"),
    SECOND("2학년"),
    THIRD("3학년"),
    FOURTH("4학년"),
    ALL("전학년"),
    ;

    private final String value;

    LectureGrade(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
