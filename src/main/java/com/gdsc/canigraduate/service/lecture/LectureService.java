/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.service.lecture;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.lecture.LectureSearchParams;
import com.gdsc.canigraduate.repository.LectureRepository;
import com.gdsc.canigraduate.repository.LectureRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureRepositoryCustom lectureRepositoryCustom;

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    public List<Lecture> findByUser(User user) {
        return lectureRepositoryCustom.findByUser(user);
    }

    public List<Lecture> findSearchedLecture(LectureSearchParams lectureSearchParams) {
        return lectureRepositoryCustom.findBySearch(lectureSearchParams);
    }
}
