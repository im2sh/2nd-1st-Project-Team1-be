package com.gdsc.canigraduate.util;

/**
 * Created by im2sh
 */

public class ExcelUtils {
    private ExcelUtils(){

    }

    /**
     *
     * @param mime
     * @param extension
     * Microsoft Office 파일의 MIME는 application/x-tika-ooxml
     * 확장자가 xls 또는 xlsx
     * @return
     */
    public static boolean isExcel(String mime, String extension){
        return mime.equals("application/x-tika-ooxml") && extension.equals("xlsx") || extension.equals("xls");
    }
}
