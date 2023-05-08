/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
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

    @Getter
    private final String value;

    static public LectureType fromValue(String value) {
        return Arrays.stream(LectureType.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(value + " type은 존재하지 않습니다."));
    }

    @Override
    public String getKey() {
        return this.name();
    }
}
