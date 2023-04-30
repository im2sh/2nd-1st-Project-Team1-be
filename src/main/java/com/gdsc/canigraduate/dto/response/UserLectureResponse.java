package com.gdsc.canigraduate.dto.response;

import lombok.Data;

@Data
public class UserLectureResponse {

    private String semester;
    private Integer majorCredit;
    private Integer cultureCredit;
    private Integer normalCredit;

    public UserLectureResponse(String semester, Integer majorCredit, Integer cultureCredit, Integer normalCredit) {
        this.semester = semester;
        this.majorCredit = majorCredit;
        this.cultureCredit = cultureCredit;
        this.normalCredit = normalCredit;
    }
}
