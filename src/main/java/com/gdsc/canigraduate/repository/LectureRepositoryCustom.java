/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.lecture.LectureSearchParams;

import java.util.List;

public interface LectureRepositoryCustom {
    List<Lecture> findByUser(User user);

    List<Lecture> findBySearch(LectureSearchParams searchParams);
}
