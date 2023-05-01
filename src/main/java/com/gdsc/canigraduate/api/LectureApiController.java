/**
 * Author: 박기현 (kiryanchi)
 */
package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.domain.lecture.Lecture;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.response.ResponseDto;
import com.gdsc.canigraduate.dto.lecture.LectureDto;
import com.gdsc.canigraduate.service.lecture.LectureService;
import com.gdsc.canigraduate.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Lecture", description = "Lecture 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class LectureApiController {

    private final UserService userService;
    private final LectureService lectureService;

    @Operation(
            summary = "모든 강의 조희",
            description = "연도, 학과에 상관없이 모든 강의를 가져옵니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "모든 강의를 가져옵니다.",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LectureDto.class)),
                            examples = @ExampleObject(
                                    value = "[\n" +
                                            "{\n" +
                                            "\"name\": \"문화 기술 개론\",\n" +
                                            "\"code\": \"CLTR0263\",\n" +
                                            "\"type\": \"기본소양\",\n" +
                                            "\"year\": \"1학년\",\n" +
                                            "\"semester\": \"1학기\",\n" +
                                            "\"credit\": \"3학점\",\n" +
                                            "\"required\": false,\n" +
                                            "\"design\": false\n" +
                                            "},\n" +
                                            "{\n" +
                                            "\"name\": \"실용화법\",\n" +
                                            "\"code\": \"CLTR0003\",\n" +
                                            "\"type\": \"기본소양\",\n" +
                                            "\"year\": \"1학년\",\n" +
                                            "\"semester\": \"2학기\",\n" +
                                            "\"credit\": \"3학점\",\n" +
                                            "\"required\": false,\n" +
                                            "\"design\": false\n" +
                                            "}\n" +
                                            "]"
                            )
                    )
            }
    )
    @GetMapping("/lectures")
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        List<Lecture> lectures = lectureService.findAll();

        List<LectureDto> body = lectures.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(body);
    }

    @Operation(
            summary = "유저에 해당하는 강의 조회",
            description = "유저의 입학년도, 학과에 맞는 강의를 가져옵니다."
    )
    @Parameter(name = "id", description = "유저의 학번", required = true)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "수업을 가져옵니다.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LectureDto.class)),
                                    examples = @ExampleObject(
                                            value = "[\n" +
                                                    "{\n" +
                                                    "\"name\": \"문화 기술 개론\",\n" +
                                                    "\"code\": \"CLTR0263\",\n" +
                                                    "\"type\": \"기본소양\",\n" +
                                                    "\"year\": \"1학년\",\n" +
                                                    "\"semester\": \"1학기\",\n" +
                                                    "\"credit\": \"3학점\",\n" +
                                                    "\"required\": false,\n" +
                                                    "\"design\": false\n" +
                                                    "},\n" +
                                                    "{\n" +
                                                    "\"name\": \"실용화법\",\n" +
                                                    "\"code\": \"CLTR0003\",\n" +
                                                    "\"type\": \"기본소양\",\n" +
                                                    "\"year\": \"1학년\",\n" +
                                                    "\"semester\": \"2학기\",\n" +
                                                    "\"credit\": \"3학점\",\n" +
                                                    "\"required\": false,\n" +
                                                    "\"design\": false\n" +
                                                    "}\n" +
                                                    "]"
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "학번에 해당하는 유저가 없습니다.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class),
                            examples = @ExampleObject(
                                    value = "{\n\"message\": \"올바른 학번이 아닙니다.\"\n}"
                            )
                    )
            )
    }
    )
    @GetMapping("/lectures/{id}")
    public ResponseEntity<?> getLecturesByUserId(@PathVariable("id") Long userId) {
        Optional<User> user = userService.findOne(userId);
        if (user.isPresent())
            return ResponseEntity.ok().body(lectureService.findByUser(user.get()));
        return ResponseEntity.badRequest().body(new ResponseDto("올바른 학번이 아닙니다."));
    }

}
