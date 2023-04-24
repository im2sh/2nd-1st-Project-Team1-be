package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by im2sh
 */

public interface UserLectureDetailRepository extends JpaRepository<UserLectureDetail, Long> {
}
