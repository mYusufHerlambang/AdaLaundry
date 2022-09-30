package com.adaLaundry.restExceptionHandler;


import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private Object messages;
    private  long timeStamp;


}
