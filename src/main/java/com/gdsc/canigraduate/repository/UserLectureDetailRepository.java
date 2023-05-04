package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by im2sh
 */

public interface UserLectureDetailRepository extends JpaRepository<UserLectureDetail, Long> {
    List<UserLectureDetail> findAllByUserLectureId(Long id);

    List<UserLectureDetail> deleteAllByUserLectureId(Long id);

    Optional<UserLectureDetail> findById(Long id);
}
