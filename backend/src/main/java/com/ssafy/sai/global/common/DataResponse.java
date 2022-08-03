package com.ssafy.sai.global.common;

import lombok.Getter;

@Getter
public class DataResponse<T> extends MessageResponse {
    private T data;
    private int count;

    public DataResponse(T data, int count) {
        super(data);
        this.count = count;
    }
}