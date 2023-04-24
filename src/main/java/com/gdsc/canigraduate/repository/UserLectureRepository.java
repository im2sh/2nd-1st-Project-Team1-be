package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by im2sh
 */

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {
    UserLecture findBySemester(String semester);

    UserLecture findByUser(User user);

}
