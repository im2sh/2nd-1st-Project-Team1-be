package com.gdsc.canigraduate.exception;

/**
 * Created by im2sh
 */

public class IncorrectPassword extends RuntimeException{
    public IncorrectPassword(String message){
        super(message);
    }
}
