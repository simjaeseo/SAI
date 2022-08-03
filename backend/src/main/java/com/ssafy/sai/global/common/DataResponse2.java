package com.ssafy.sai.global.common;

import lombok.Getter;

@Getter
public class DataResponse2<T> extends MessageResponse2{
    private T data;

    public DataResponse2(int code, String message, T data){
        super(code, message);
        this.data = data;
    }
}