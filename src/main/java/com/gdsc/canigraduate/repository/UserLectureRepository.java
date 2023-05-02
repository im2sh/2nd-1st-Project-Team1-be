package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by im2sh
 */

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {
    UserLecture findBySemesterAndUserId(String semester, Long userId);

    List<UserLecture> findByUserId(Long userId);

}
