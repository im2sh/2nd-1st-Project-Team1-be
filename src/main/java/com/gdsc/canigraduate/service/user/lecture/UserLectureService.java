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

    public UserLecture findLectureBySemesterAndUserId(String semester, Long id){
        return userLectureRepository.findBySemesterAndUserId(semester,id);
    }
//    public UserLecture findUserLectureByDetail(Long id){
//        return userLectureRepository.findByLectureId(id);
//    }
    @Transactional
    public void dividedLecture(String token, List<UserLectureDetailDTO> dto){
        User user = userRepository.findUserByToken(token);
        Integer credit = 0;
        Integer totalCredit = 0;
        Integer totalSemester = 0;

        for (UserLectureDetailDTO detail : dto) {
            UserLectureDetail userLectureDetail = detail.toEntity(detail);
            String s = detail.YearToString(detail.getYear(), detail.getSemester());
            credit = detail.getCredit();
            totalCredit += credit;

            if(userLectureRepository.findBySemesterAndUserId(s, user.getId()) == null){
                totalSemester += 1;
                UserLecture userLecture = new UserLecture(user, s);
                userLecture.addUserLectureDetail(userLectureDetail);
                userLectureDetail.setUserLecture(userLecture);
                userLectureDetail.setType(userLectureDetail.getMajor());
                userLecture.addCredit(credit, userLectureDetail.getUserLectureType());
                userService.userDetailCreditCal(user, credit, userLectureDetail.getUserLectureType());
                userLectureDetailRepository.save(userLectureDetail);
            }
            else{
                UserLecture userLecture = findLectureBySemesterAndUserId(s, user.getId());
                userLecture.addUserLectureDetail(userLectureDetail);
                userLectureDetail.setUserLecture(userLecture);
                userLectureDetail.setType(userLectureDetail.getMajor());
                userLecture.addCredit(credit, userLectureDetail.getUserLectureType());
                userService.userDetailCreditCal(user, credit, userLectureDetail.getUserLectureType());
                userLectureDetailRepository.save(userLectureDetail);
            }
        }

        user.setProfile(totalCredit, totalSemester);
        user.userUpload(true);
        userRepository.save(user);
    }


    public void deleteByLecture(UserLecture userLecture){
        userLectureRepository.delete(userLecture);
    }
    public void delete_all_lecture(Long userId){
        userLectureRepository.deleteAllByUserId(userId);
    }
    @Transactional
    public void lecture_delete(User user){
        Integer totalCredit = 0;
        Integer totalSemester = 0;
        List<UserLecture> userLectures = findAllByUserId(user.getId());
        for (UserLecture userLecture : userLectures) {
            List<UserLectureDetail> userLectureDetails = userLectureDetailRepository.findAllByUserLectureId(userLecture.getId());
            totalSemester+=1;
            for (UserLectureDetail userLectureDetail : userLectureDetails) {
                totalCredit += userLectureDetail.getCredit();
                userLectureDetailRepository.delete(userLectureDetail);
            }
            deleteByLecture(userLecture);
        }
        user.subSemester(totalSemester);
        user.subCredit(totalCredit);
        user.InitCredit();
        user.userUpload(false);
    }

    public UserLecture findByDetail(Long id){
        return userLectureRepository.findOneById(id);
    }
}

