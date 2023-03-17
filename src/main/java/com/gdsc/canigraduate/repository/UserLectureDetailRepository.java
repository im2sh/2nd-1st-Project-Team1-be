package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLectureDetailRepository extends JpaRepository<UserLectureDetail, Long> {
}
