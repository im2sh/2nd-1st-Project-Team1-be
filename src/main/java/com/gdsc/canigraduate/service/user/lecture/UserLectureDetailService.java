package com.gdsc.canigraduate.service.user.lecture;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.repository.UserLectureDetailRepository;
import com.gdsc.canigraduate.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by im2sh
 */

@Service
@RequiredArgsConstructor
@Transactional
public class UserLectureDetailService {

    private final UserLectureDetailRepository userLectureDetailRepository;
    private final UserLectureService userLectureService;
    private final UserService userService;

    public Long save(UserLectureDetail detail){
        return userLectureDetailRepository.save(detail).getId();
    }

    public List<UserLectureDetail> findAllByLectureID(Long lectureId){
        return userLectureDetailRepository.findAllByUserLectureId(lectureId);
    }

    @Transactional
    public boolean delete_one(Long detailId){
        System.out.println("------------------------delete Test------------------------");
        Optional<UserLectureDetail> detail = userLectureDetailRepository.findById(detailId);
        if(detail == null)
            return false;
        UserLectureDetail lectureDetail = detail.get();
        UserLecture userLecture = userLectureService.findByDetail(lectureDetail.getUserLecture().getId());
        userLecture.subCredit(lectureDetail.getCredit(), lectureDetail.getUserLectureType());
        Optional<User> one = userService.findOne(userLecture.getUser().getId());
        if(one == null)
            return false;
        User user = one.get();
        user.subCredit(lectureDetail.getCredit());
        userLectureDetailRepository.deleteById(lectureDetail.getId());
        System.out.println(lectureDetail.getId());
        System.out.println("------------------------delete Test------------------------");
        return true;
    }

}
