package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.dto.response.ResponseDto;
import com.gdsc.canigraduate.dto.excel.ExcelData;
import com.gdsc.canigraduate.dto.response.UserLectureDetailResponse;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import com.gdsc.canigraduate.dto.response.UserLectureResponse;
import com.gdsc.canigraduate.service.user.UserService;
import com.gdsc.canigraduate.service.user.lecture.FileService;
import com.gdsc.canigraduate.service.user.lecture.UserLectureDetailService;
import com.gdsc.canigraduate.service.user.lecture.UserLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by im2sh
 */

@Controller
@RequiredArgsConstructor
public class UserLectureController {
    private final FileService fileService;
    private final UserService userService;
    private final UserLectureDetailService userLectureDetailService;
    private final UserLectureService userLectureService;

    @PostMapping("excel/read/{token}")
    public ResponseEntity<ResponseDto> readExcel(@PathVariable("token") String token, @RequestParam("file")MultipartFile file) throws IOException {
        List<UserLectureDetailDTO> details = new ArrayList<>();

        List<ExcelData> dataList = fileService.readExcel(file);
        for (ExcelData excelData : dataList) {
            UserLectureDetailDTO detailDTO = excelData.toDetailDTO(excelData);
            details.add(detailDTO);
        }

        userLectureService.dividedLecture(token, details);

        return ResponseEntity.ok().body(new ResponseDto("엑셀파일이 업로드 되었습니다"));
    }


    @GetMapping("/userLecture/{id}")
    public ResponseEntity<List<UserLectureResponse>> getUserLecture(@PathVariable("id")Long id){
        Optional<User> one = userService.findOne(id);
        User user = one.get();
        if(user == null){
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        List<UserLecture> userLectureList = userLectureService.findAllByUserId(user.getId());
        List<UserLectureResponse> userLectureResponseList = new ArrayList<>();
        for (UserLecture lecture : userLectureList) {
            UserLectureResponse response = lecture.toResponse();
            userLectureResponseList.add(response);
        }
         return ResponseEntity.ok().body(userLectureResponseList);
    }

    @GetMapping("/userLecture/{id}/{LectureID}")
    public ResponseEntity<List<UserLectureDetailResponse>> getDetail(@PathVariable("id")Long id, @PathVariable("LectureID")Long lectureId){
        Optional<User> one = userService.findOne(id);
        User user = one.get();
        if(user == null){
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        List<UserLectureDetail> detailList = userLectureDetailService.findAllByLectureID(lectureId);
        List<UserLectureDetailResponse> userLectureDetailResponseList = new ArrayList<>();
        for (UserLectureDetail detail : detailList) {
            UserLectureDetailResponse response = detail.toResponse();
            userLectureDetailResponseList.add(response);
        }
        return ResponseEntity.ok().body(userLectureDetailResponseList);
    }
}


