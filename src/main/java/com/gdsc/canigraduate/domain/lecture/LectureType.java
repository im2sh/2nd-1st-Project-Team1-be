/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;

public enum LectureType implements BaseEnum {
    // 심컴
    BASIC_KNOWLEDGE("기본소양"),
    MAJOR_BASE("전공기본"),
    ENGINEERING_MAJOR("공학전공"),
    TEACHER("교직"),

    // 글솦
    LIBERAL("교양"),
    MAJOR("전공"),
    NORMAL("일반선택"),
    ;

    private final String value;

    LectureType(String value) {
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
