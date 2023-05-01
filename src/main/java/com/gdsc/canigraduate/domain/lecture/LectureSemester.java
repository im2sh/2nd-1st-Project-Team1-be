/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;

public enum LectureSemester implements BaseEnum {
    FIRST("1학기"),
    SECOND("2학기"),
    ALL("1-2학기"),
    SUMMER("여름학기"),
    WINTER("겨울학기"),
    ;

    private final String value;

    LectureSemester(String value) {
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
