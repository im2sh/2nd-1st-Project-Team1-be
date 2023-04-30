package com.gdsc.canigraduate.service.user.lecture;

import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.repository.UserLectureDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by im2sh
 */

@Service
@RequiredArgsConstructor
@Transactional
public class UserLectureDetailService {

    private final UserLectureDetailRepository userLectureDetailRepository;

    public Long save(UserLectureDetail detail){
        return userLectureDetailRepository.save(detail).getId();
    }

    public List<UserLectureDetail> findAllByLectureID(Long lectureId){
        return userLectureDetailRepository.findAllByUserLectureId(lectureId);
    }
}
