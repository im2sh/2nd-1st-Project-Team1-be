package com.gdsc.canigraduate.service.user.lecture;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import com.gdsc.canigraduate.repository.UserLectureDetailRepository;
import com.gdsc.canigraduate.repository.UserLectureRepository;
import com.gdsc.canigraduate.repository.UserRepository;
import com.gdsc.canigraduate.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by im2sh
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLectureService {
    private final UserLectureRepository userLectureRepository;

    private final UserLectureDetailRepository userLectureDetailRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * 사용자 lecture 기입
     */
    @Transactional
    public Long save(UserLecture userLecture) {
        return userLectureRepository.save(userLecture).getId();
    }

    public List<UserLecture> findAllByUserId(Long userId){
        return userLectureRepository.findByUserId(userId);
    }

    public Optional<UserLecture> findLectureByUser(User user) {
        return userLectureRepository.findById(user.getId());
    }
    @Transactional
    public void dividedLecture(String token, List<UserLectureDetailDTO> dto){
        User user = userRepository.findUserByToken(token);
        Integer credit = 0;
        Integer totalCredit = 0;
        Integer totalSemester = 0;
        List<UserLecture> userLectureList = new ArrayList<>();

        for (UserLectureDetailDTO detail : dto) {
            UserLectureDetail userLectureDetail = detail.toEntity(detail);
            String s = detail.YearToString(detail.getYear(), detail.getSemester());
            credit = detail.getCredit();
            totalCredit += credit;
            List<UserLecture> allByUserId = findAllByUserId(user.getId());
            if(userLectureRepository.findBySemesterAndUserId(s, user.getId()) == null){
                totalSemester += 1;
                UserLecture userLecture = new UserLecture(user, s);
                userLecture.addUserLectureDetail(userLectureDetail);
                userLectureDetail.setUserLecture(userLecture);
                userLectureDetail.setType(userLectureDetail.getMajor());
                userLecture.setCredit(credit, userLectureDetail.getUserLectureType());
                userLectureDetailRepository.save(userLectureDetail);
            }
            else{
                UserLecture userLecture = userLectureRepository.findBySemesterAndUserId(s, user.getId());
                userLecture.addUserLectureDetail(userLectureDetail);
                userLectureDetail.setUserLecture(userLecture);
                userLectureDetail.setType(userLectureDetail.getMajor());
                userLecture.setCredit(credit, userLectureDetail.getUserLectureType());
                userLectureDetailRepository.save(userLectureDetail);
            }
        }

        user.setProfile(totalCredit, totalSemester);
        userRepository.save(user);
    }
}

