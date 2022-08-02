package com.ssafy.sai.global.common;

import lombok.Getter;

@Getter
public class MessageResponse {
    private int code;
    private String message;

    public MessageResponse() {

    }
    public MessageResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
}