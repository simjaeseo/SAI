package com.ssafy.sai.global.common;

import lombok.Getter;

@Getter
public class CountDataResponse<T> extends DataResponse {
    private T data;
    private int count;

    public CountDataResponse(T data, int count) {
        super(data);
        this.count = count;
    }
}