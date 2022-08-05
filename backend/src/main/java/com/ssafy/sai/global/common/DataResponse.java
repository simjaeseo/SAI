package com.ssafy.sai.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class DataResponse<T> extends MessageResponse {
    private T data;

    public DataResponse(T data) {
        super();
        this.data = data;
    }
}