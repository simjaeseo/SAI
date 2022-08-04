package com.ssafy.sai.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponse<T> {

    private HttpStatus httpStatus;
    private String message;

    public MessageResponse(){
        this.httpStatus = HttpStatus.OK;
        this.message = "success";
    }
}