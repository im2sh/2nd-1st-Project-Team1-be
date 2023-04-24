package com.gdsc.canigraduate.dto.user;

import lombok.Data;

/**
 * Created by im2sh
 */

@Data
public class UserLoginRequest {
    private String classId;
    private String userPw;
}
