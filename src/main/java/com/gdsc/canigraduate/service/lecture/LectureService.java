package com.gdsc.canigraduate.service.lecture;

import com.gdsc.canigraduate.domain.lecture.LectureDetail;
import com.gdsc.canigraduate.domain.user.Member;
import com.gdsc.canigraduate.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;


}
