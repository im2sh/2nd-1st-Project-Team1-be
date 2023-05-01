/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;

import java.util.List;

public interface LectureRepositoryCustom {
    List<Lecture> findByUser(User user);
}
