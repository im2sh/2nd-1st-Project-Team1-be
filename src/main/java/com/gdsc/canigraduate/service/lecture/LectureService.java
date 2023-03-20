package com.gdsc.canigraduate.service.lecture;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import com.gdsc.canigraduate.repository.UserLectureDetailRepository;
import com.gdsc.canigraduate.repository.UserLectureRepository;
import com.gdsc.canigraduate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public Long save(UserLecture userLecture) {
        return userLectureRepository.save(userLecture).getId();
    }

    public Optional<UserLecture> findLectureByUser(User user) {
        return userLectureRepository.findById(user.getId());
    }

    public void dividedLecture(String token, List<UserLectureDetailDTO> dto){
        User user = userRepository.findUserByToken(token);
        for (UserLectureDetailDTO detail : dto) {
            UserLectureDetail userLectureDetail = detail.toEntity(detail);
            String s = detail.YearToString(detail.getYear(), detail.getSemester());

            if(userLectureRepository.findBySemester(s) == null){
                UserLecture userLecture = new UserLecture(user);
                userLecture.addUserLectureDetail(userLectureDetail);
                userLecture.InitLecture(s);
                userLectureDetail.setUserLecture(userLecture);
                userLectureDetail.setType(userLectureDetail.getMajor());
                userLectureDetailRepository.save(userLectureDetail);
                save(userLecture);
            }
            else{
                UserLecture userLecture = userLectureRepository.findBySemester(s);
                userLecture.addUserLectureDetail(userLectureDetail);
                userLectureDetail.setUserLecture(userLecture);
                userLectureDetail.setType(userLectureDetail.getMajor());
                userLectureDetailRepository.save(userLectureDetail);
            }
        }

    }
}

