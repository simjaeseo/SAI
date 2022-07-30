package com.ssafy.sai.global.common;

public class DataResponse<T> extends MessageResponse{
    private T data;

    public DataResponse(int code, String message, T data){
        super(code, message);
        this.data = data;
    }
}