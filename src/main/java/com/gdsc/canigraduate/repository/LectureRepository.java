package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
