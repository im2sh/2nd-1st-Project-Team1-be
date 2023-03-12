package com.gdsc.canigraduate.dto.user;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String classId;
    private String userPw;
}
