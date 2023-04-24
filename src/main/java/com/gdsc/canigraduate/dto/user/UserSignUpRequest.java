package com.gdsc.canigraduate.dto.user;

import com.gdsc.canigraduate.domain.Department;
import com.gdsc.canigraduate.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by im2sh
 */

@Data
@NoArgsConstructor
public class UserSignUpRequest {
    @NotBlank(message = "학번을 입력해주세요.")
    private String classId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPw;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    private Department department;

    private String token;

    public User toEntity(){
        User user = User.builder()
                .name(name)
                .classId(classId)
                .userPw(userPw)
                .department(department)
                .token(UUID.randomUUID().toString())
                .admissionYear(Integer.valueOf(classId.substring(0, 4)))
                .build();
        return user;
    }
}

