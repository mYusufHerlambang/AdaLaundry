package com.adaLaundry.restExceptionHandler;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
