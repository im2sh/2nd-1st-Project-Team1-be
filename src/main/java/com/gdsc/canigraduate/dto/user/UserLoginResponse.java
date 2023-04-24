package com.gdsc.canigraduate.dto.user;

import lombok.Data;

/**
 * Created by im2sh
 */

@Data
public class UserLoginResponse {
    private String classId;

    private String name;

    private String token;

    public UserLoginResponse(String classId, String name, String token) {
        this.classId = classId;
        this.name = name;
        this.token = token;
    }
}
