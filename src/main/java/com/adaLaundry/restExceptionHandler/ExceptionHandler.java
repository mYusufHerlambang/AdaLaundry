package com.adaLaundry.restExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundHandle(NotFoundException notFoundException){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessages(notFoundException.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> validationExceptionHandler(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = null;
                    String errorMessage = null;

                    if(error instanceof FieldError){
                        fieldName = ((FieldError) error).getField();
                        errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    } else {
                        fieldName = error.getObjectName();
                        errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    }
                });

        ErrorResponse errorResponseValidation = new ErrorResponse();
        errorResponseValidation.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseValidation.setMessages(errors);
        errorResponseValidation.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponseValidation,HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> invalidUsernamePass(InvalidUsernameOrPassword invalidUsernameOrPassword){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessages(invalidUsernameOrPassword.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> internalServerError(InternalServerError internalServerError){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessages(internalServerError.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
