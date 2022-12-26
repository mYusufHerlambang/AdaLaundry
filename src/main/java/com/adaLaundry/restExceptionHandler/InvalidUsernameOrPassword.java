package com.adaLaundry.restExceptionHandler;

public class InvalidUsernameOrPassword extends RuntimeException{
    public InvalidUsernameOrPassword(String message){
        super(message);
    }
}
