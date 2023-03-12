package com.gdsc.canigraduate.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String message;

    public ResponseDto(String message){
        this.message = message;
    }
}
