package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.lecture.QLecture;
import com.gdsc.canigraduate.domain.user.QUser;
import com.gdsc.canigraduate.domain.user.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Lecture> findByUser(User user) {
        QLecture qLecture = QLecture.lecture;
        QUser qUser = QUser.user;

        return jpaQueryFactory.selectFrom(qLecture)
                .where(
                        qLecture.lectureYear.eq(
                                user.getAdmissionYear()
                        )
                )
                .fetch();
    }
}
