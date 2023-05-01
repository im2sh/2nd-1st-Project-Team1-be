/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum LectureSemester implements BaseEnum {
    FIRST("1학기"),
    SECOND("2학기"),
    ALL("1-2학기"),
    SUMMER("여름학기"),
    WINTER("겨울학기"),
    ;

    @Getter
    private final String value;

    static public LectureSemester fromValue(String value) {
        return Arrays.stream(LectureSemester.values())
                .filter(semester -> semester.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(value + " semester는 존재하지 않습니다."));
    }

    @Override
    public String getKey() {
        return this.name();
    }
}
