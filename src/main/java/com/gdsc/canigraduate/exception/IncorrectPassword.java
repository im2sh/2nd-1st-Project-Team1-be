package com.gdsc.canigraduate.exception;

public class IncorrectPassword extends RuntimeException{
    public IncorrectPassword(String message){
        super(message);
    }
}
