package com.gdsc.canigraduate.service.user;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.user.UserSignUpRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        userSignUpRequest.setClassId("2020118198");
        userSignUpRequest.setName("이석환");
        userSignUpRequest.setUserPw("1234");
        //when
        Long id = userService.join(userSignUpRequest);

        //then
        Optional<User> user = userService.findOne(id);
        System.out.println(user.get().getClassId());
        System.out.println(userSignUpRequest.getClassId());

        Assertions.assertThat(user.get().getClassId()).isEqualTo(userSignUpRequest.getClassId());
        Assertions.assertThat(user.get().getAdmissionYear()).isEqualTo(2020);
    }

}