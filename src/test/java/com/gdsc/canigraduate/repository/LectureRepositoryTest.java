package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LectureRepositoryTest {

    private LectureRepository lectureRepository;

    @Test
    void findByUserUsingQuerydslTest() {
    }
}
