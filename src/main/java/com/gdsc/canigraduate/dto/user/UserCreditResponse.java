package com.gdsc.canigraduate.dto.user;


import lombok.Data;

@Data
public class UserCreditResponse {
    public Integer majorCredit;
    public Integer cultureCredit;
    public Integer normalCredit;
    public Integer basicMajorCredit;
    public Integer techMajorCredit;

    public Integer presentCredit;

    public UserCreditResponse(Integer majorCredit, Integer cultureCredit, Integer normalCredit, Integer basicMajorCredit, Integer techMajorCredit, Integer presentCredit) {
        this.majorCredit = majorCredit;
        this.cultureCredit = cultureCredit;
        this.normalCredit = normalCredit;
        this.basicMajorCredit = basicMajorCredit;
        this.techMajorCredit = techMajorCredit;
        this.presentCredit = presentCredit;
    }
}
