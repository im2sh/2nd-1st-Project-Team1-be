package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.lecture.LectureDto;
import com.gdsc.canigraduate.service.lecture.LectureService;
import com.gdsc.canigraduate.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class LectureApiController {

    private final UserService userService;
    private final LectureService lectureService;

    @GetMapping("/lectures")
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        List<Lecture> lectures = lectureService.findAll();

        List<LectureDto> body = lectures.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/lectures/{id}")
    public ResponseEntity<List<Lecture>> getLecturesByUserId(@PathVariable("id") Long userId) {
        Optional<User> user = userService.findOne(userId);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        List<Lecture> lectures = lectureService.findByUser(user.get());
        return ResponseEntity.ok().body(lectures);
    }

}
