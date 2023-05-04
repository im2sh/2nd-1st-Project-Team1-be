package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by im2sh
 */

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByClassId(String classId);
    Optional<User> findById(Long id);
    User findUserByToken(String Token);
    List<UserLecture> findUserLectureListById(Long id);

    List<UserLecture> deleteAllULById(Long userId);
}
