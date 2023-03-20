package com.gdsc.canigraduate.service.lecture;

import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.dto.excel.ExcelData;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import com.gdsc.canigraduate.repository.UserLectureDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureDetailService {

    private final UserLectureDetailRepository userLectureDetailRepository;

    public Long save(UserLectureDetail detail){
        return userLectureDetailRepository.save(detail).getId();
    }
}
