package com.gdsc.canigraduate.dto.user;

import com.gdsc.canigraduate.domain.user.UserGraduationInfo;
import lombok.Data;

@Data
public class UserGraduationInfoRequest {
    public boolean basic;
    public boolean coreSociety;
    public boolean coreScience;
    public boolean culture;
    public boolean english;
    public boolean intern;
    public boolean consulting;

    public UserGraduationInfo toEntity(boolean basic, boolean coreSociety, boolean coreScience, boolean culture, boolean english, boolean intern, boolean consulting){
        UserGraduationInfo userGraduationInfo = UserGraduationInfo.builder().basic(basic).coreSociety(coreSociety).coreScience(coreScience).culture(culture).english(english).intern(intern).consulting(consulting).build();
        return userGraduationInfo;
    }
}
