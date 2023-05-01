/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum LectureCredit implements BaseEnum {
    ONE("1학점"),
    TWO("2학점"),
    THREE("3학점"),
    FOUR("4학점"),
    FIVE("5학점"),
    SIX("6학점"),
    ;

    @Getter
    private final String value;

    static public LectureCredit fromValue(String value) {
        return Arrays.stream(LectureCredit.values())
                .filter(credit -> credit.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(value + " credit은 존재하지 않습니다."));
    }

    @Override
    public String getKey() {
        return this.name();
    }
}
