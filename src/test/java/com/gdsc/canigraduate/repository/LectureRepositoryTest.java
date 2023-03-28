package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.user.UserSignUpRequest;
import com.gdsc.canigraduate.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LectureRepositoryTest {

    @Autowired private UserService userService;
    @Autowired private LectureRepositoryCustom lectureRepositoryCustom;

    @Test
    void findByUserUsingQuerydslTest() {
        //given
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        userSignUpRequest.setClassId("2020118198");
        userSignUpRequest.setName("이석환");
        userSignUpRequest.setUserPw("1234");
        //when
        Long id = userService.join(userSignUpRequest);

        //then
        Optional<User> user = userService.findOne(id);
        if (user.isPresent()) {
            List<Lecture> lectures = lectureRepositoryCustom.findByUser(user.get());
            lectures.forEach(lecture -> {
                Assertions.assertThat(lecture.getLectureYear()).isEqualTo(
                        user.get().getAdmissionYear()
                );
            });
        }
    }
}
