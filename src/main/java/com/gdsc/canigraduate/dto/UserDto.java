package com.gdsc.canigraduate.dto;

import com.gdsc.canigraduate.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String classId;
    private String userPw;
    private String name;

    public UserDto(User user){
        classId = user.getClassId();
        userPw = user.getUserPw();
        name = user.getName();
    }

    @Builder
    public UserDto(String classId, String pw, String name){
        this.classId = classId;
        this.userPw = pw;
        this.name = name;
    }

    public User toEntity(){
        User user = User.builder()
                .name(name)
                .classId(classId)
                .userPw(userPw).build();
        return user;
    }
}
