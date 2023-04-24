package com.gdsc.canigraduate.service.user.lecture;

import com.gdsc.canigraduate.dto.excel.ExcelData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.gdsc.canigraduate.util.ExcelUtils.isExcel;

/**
 * Created by im2sh
 */

@Service
@RequiredArgsConstructor
public class FileService {
    public List<ExcelData> readExcel(MultipartFile file) throws IOException {
        Tika tika = new Tika();
        String detect = tika.detect(file.getBytes()); // MIME 타입 얻기

        List<ExcelData> dataList = new ArrayList<>();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!isExcel(detect, extension)) {
            throw new IOException("엑셀 파일이 아닙니다. 엑셀 파일만 업로드해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        assert workbook != null;
        Sheet worksheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row != null) {
                ExcelData data = new ExcelData();

                data.setYear(Integer.valueOf(formatter.formatCellValue(row.getCell(0))));
                data.setSemester(row.getCell(1).getStringCellValue());
                data.setMajor(row.getCell(2).getStringCellValue());
                data.setCode(row.getCell(3).getStringCellValue());
                data.setLectureName(row.getCell(4).getStringCellValue());
                data.setCredit(Integer.valueOf(formatter.formatCellValue(row.getCell(5))));
                data.setGrade(row.getCell(6).getStringCellValue());
                data.setScore(Double.valueOf(formatter.formatCellValue(row.getCell(7))));

                dataList.add(data);
            }
        }


        return dataList;
    }
}
