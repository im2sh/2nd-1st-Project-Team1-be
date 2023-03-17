package com.gdsc.canigraduate.service.lecture;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDto;
import com.gdsc.canigraduate.repository.UserLectureDetailRepository;
import com.gdsc.canigraduate.repository.UserLectureRepository;
import com.gdsc.canigraduate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureService {
    private final UserLectureRepository userLectureRepository;
    private final UserLectureDetailRepository userLectureDetailRepository;
    private final UserRepository userRepository;

    /**
     * 사용자 lecture 기입
     */
//    @Transactional
//    public Long save() {
//        //return userLectureRepository.save(userLecture).getId();
//    }

    public Optional<UserLecture> findLectureByUser(User user) {
        return userLectureRepository.findById(user.getId());
    }

}
