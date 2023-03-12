package com.gdsc.canigraduate.service.member;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.UserDto;
import com.gdsc.canigraduate.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    @Transactional
    @Rollback
    public void 회원가입() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setClassId("2020118198");
        userDto.setName("이석환");
        userDto.setUserPw("1234");
        //when
        Long id = userService.join(userDto);

        //then
        Optional<User> user = userService.findOne(id);
        System.out.println(user.get().getClassId());
        System.out.println(userDto.getClassId());

        Assertions.assertThat(user.get().getClassId()).isEqualTo(userDto.getClassId());
    }

}