package com.ssafy.sai.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class MessageResponse2 {
    private int code;
    private String message;

    public MessageResponse2(int code, String message){
        this.code = code;
        this.message = message;
    }
}