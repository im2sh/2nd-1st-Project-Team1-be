/**
 * Author: 박기현 (kiryanchi)
 */

package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.*;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.lecture.LectureSearchParams;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gdsc.canigraduate.domain.lecture.QLecture.lecture;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Lecture> findByUser(User user) {

        return jpaQueryFactory.selectFrom(lecture)
                .where(
                        lecture.lectureYear.eq(
                                user.getAdmissionYear()
                        ).and(
                                lecture.department.eq(
                                        user.getDepartment()
                                )
                        )
                )
                .fetch();
    }

    @Override
    public List<Lecture> findBySearch(LectureSearchParams searchParams) {
        return jpaQueryFactory.selectFrom(lecture)
                .where(
                        containName(searchParams.getName()),
                        eqCode(searchParams.getCode()),
                        eqLectureYear(searchParams.getLectureYear()),
                        eqCredit(searchParams.getCredit()),
                        eqGrade(searchParams.getGrade()),
                        eqSemester(searchParams.getSemester()),
                        eqType(searchParams.getType()),
                        eqRequired(searchParams.getRequired()),
                        eqDesign(searchParams.getDesign())
                )
                .fetch();
    }

    private BooleanExpression containName(String name) {
        return isValue(name) ? lecture.name.contains(name) : null;
    }

    private BooleanExpression eqCode(String code) {
        return isValue(code) ? lecture.code.eq(code) : null;
    }

    private BooleanExpression eqLectureYear(Integer year) {
        return year != null ? lecture.lectureYear.eq(year) : null;
    }

    private BooleanExpression eqCredit(LectureCredit credit) {
        return credit != null ? lecture.credit.eq(credit) : null;
    }

    private BooleanExpression eqGrade(LectureGrade grade) {
        return grade != null ? lecture.grade.eq(grade) : null;
    }

    private BooleanExpression eqSemester(LectureSemester semester) {
        return semester != null ? lecture.semester.eq(semester) : null;
    }

    private BooleanExpression eqType(LectureType type) {
        return type != null ? lecture.type.eq((type)) : null;
    }

    private BooleanExpression eqRequired(Boolean required) {
        return required != null ? lecture.required.eq(required) : null;
    }

    private BooleanExpression eqDesign(Boolean design) {
        return design != null ? lecture.design.eq(design) : null;
    }

    private Boolean isValue(String value) {
        return !(value == null || value.isBlank());
    }
}
