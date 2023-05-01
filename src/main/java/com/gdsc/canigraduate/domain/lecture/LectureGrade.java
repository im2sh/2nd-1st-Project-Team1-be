/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.domain.lecture;

import com.gdsc.canigraduate.domain.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LectureGrade implements BaseEnum {
    FIRST("1학년"),
    SECOND("2학년"),
    THIRD("3학년"),
    FOURTH("4학년"),
    ALL("전학년"),
    ;

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return this.name();
    }
}
