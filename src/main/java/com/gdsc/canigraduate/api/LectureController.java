package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.dto.ResponseDto;
import com.gdsc.canigraduate.dto.excel.ExcelData;
import com.gdsc.canigraduate.dto.userLecture.UserLectureDetailDTO;
import com.gdsc.canigraduate.service.lecture.FileService;
import com.gdsc.canigraduate.service.lecture.LectureDetailService;
import com.gdsc.canigraduate.service.lecture.LectureService;
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

@Controller
@RequiredArgsConstructor
public class LectureController {
    private final FileService fileService;
    private final LectureDetailService lectureDetailService;
    private final LectureService lectureService;

    @PostMapping("excel/read/{token}")
    public ResponseEntity<ResponseDto> readExcel(@PathVariable("token") String token, @RequestParam("file")MultipartFile file) throws IOException {
        List<UserLectureDetailDTO> details = new ArrayList<>();

        List<ExcelData> dataList = fileService.readExcel(file);
        for (ExcelData excelData : dataList) {
            UserLectureDetailDTO detailDTO = excelData.toDetailDTO(excelData);
            details.add(detailDTO);
        }
        lectureService.dividedLecture(token, details);
        return ResponseEntity.ok().body(new ResponseDto("엑셀파일이 업로드 되었습니다"));
    }

}
