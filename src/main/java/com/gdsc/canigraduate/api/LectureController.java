package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.dto.ResponseDto;
import com.gdsc.canigraduate.dto.excel.ExcelData;
import com.gdsc.canigraduate.service.lecture.FileService;
import com.gdsc.canigraduate.service.lecture.LectureDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LectureController {
    private final FileService fileService;
    private final LectureDetailService lectureDetailService;

    @PostMapping("excel/read/{token}")
    public ResponseEntity<ResponseDto> readExcel(@PathVariable("token") String token, @RequestParam("file")MultipartFile file) throws IOException {
        List<ExcelData> dataList = fileService.readExcel(file);
        for (ExcelData excelData : dataList) {
            lectureDetailService.save(excelData);

        }
        return ResponseEntity.ok().body(new ResponseDto("엑셀파일이 업로드 되었습니다"));
    }
}
