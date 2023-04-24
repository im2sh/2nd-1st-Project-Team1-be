package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.dto.ResponseDto;
import com.gdsc.canigraduate.dto.excel.ExcelData;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import com.gdsc.canigraduate.service.user.UserService;
import com.gdsc.canigraduate.service.user.lecture.FileService;
import com.gdsc.canigraduate.service.user.lecture.UserLectureDetailService;
import com.gdsc.canigraduate.service.user.lecture.UserLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

}
