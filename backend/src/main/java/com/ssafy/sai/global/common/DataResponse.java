package com.ssafy.sai.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class DataResponse<T> extends MessageResponse {

    private HttpStatus httpStatus;
    private String message;
    private T data;

    public DataResponse(T data){
        super();
        this.data = data;
    }
}