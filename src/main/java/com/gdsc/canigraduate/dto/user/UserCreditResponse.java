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

    public Integer restMajor;
    public Integer restCulture;
    public Integer restNormal;
    public Integer restBasicMajor;
    public Integer restTechMajor;
    public Integer restPresent;

    public UserCreditResponse(Integer majorCredit, Integer cultureCredit, Integer normalCredit, Integer basicMajorCredit, Integer techMajorCredit, Integer presentCredit, Integer restMajor, Integer restCulture, Integer restNormal, Integer restBasicMajor, Integer restTechMajor, Integer restPresent) {
        this.majorCredit = majorCredit;
        this.cultureCredit = cultureCredit;
        this.normalCredit = normalCredit;
        this.basicMajorCredit = basicMajorCredit;
        this.techMajorCredit = techMajorCredit;
        this.presentCredit = presentCredit;

        this.restMajor = restMajor;
        this.restCulture = restCulture;
        this.restNormal = restNormal;
        this.restBasicMajor = restBasicMajor;
        this.restTechMajor = restTechMajor;
        this.restPresent = restPresent;
    }
}
