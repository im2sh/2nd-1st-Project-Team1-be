package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.lecture.LectureDto;
import com.gdsc.canigraduate.repository.LectureRepository;
import com.gdsc.canigraduate.repository.LectureRepositoryCustom;
import com.gdsc.canigraduate.service.lecture.LectureService;
import com.gdsc.canigraduate.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class LectureApiController {

    private final UserService userService;
    private final LectureService lectureService;
    private final LectureRepository lectureRepository;
    private final LectureRepositoryCustom lectureRepositoryCustom;

    @GetMapping("/lectures")
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAll();
        ArrayList<LectureDto> body = new ArrayList<>();
        lectures.forEach(lecture -> {
            body.add(new LectureDto(lecture));
        });
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/lectures/{id}")
    public ResponseEntity<List<Lecture>> getLecturesByUserId(@PathVariable("id") Long userId) {
        Optional<User> user = userService.findOne(userId);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        List<Lecture> lectures = lectureRepositoryCustom.findByUser(user.get());
        return ResponseEntity.ok().body(lectures);
    }

}
