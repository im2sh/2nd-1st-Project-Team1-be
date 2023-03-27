package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;

public enum LectureCredit implements BaseEnum {
    ONE("1학점"),
    TWO("2학점"),
    THREE("3학점"),
    FOUR("4학점"),
    FIVE("5학점"),
    SIX("6학점"),
    ;

    private final String value;

    LectureCredit(String value) {
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
