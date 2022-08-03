package com.ssafy.sai.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class MessageResponse<T> {

    private HttpStatus httpStatus;
    private String message;
    private T data;

    public MessageResponse(T data){
        this.httpStatus = HttpStatus.OK;
        this.message = "success";
        this.data = data;
    }
}