package com.gdsc.canigraduate.service.lecture;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.LectureDto;
import com.gdsc.canigraduate.repository.LectureRepository;
import com.gdsc.canigraduate.repository.UserRepository;

import com.gdsc.canigraduate.domain.lecture.LectureDetail;
import com.gdsc.canigraduate.repository.LectureRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    /**
     * 사용자 lecture 기입
     *
     */
    @Transactional
    public Long joinLecture(LectureDto lectureDto){
        Lecture lecture = lectureDto.toEntity();
        return lectureRepository.save(lecture).getId();
    }

    public Optional<Lecture> findLectureByUser(User user){
        return lectureRepository.findById(user.getId());
    }

}
