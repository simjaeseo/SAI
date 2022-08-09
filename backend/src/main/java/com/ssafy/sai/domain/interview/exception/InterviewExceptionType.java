package com.ssafy.sai.domain.interview.exception;

import com.ssafy.sai.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum InterviewExceptionType implements BaseExceptionType {

    NOT_FOUND_S3_VIDEO(404, HttpStatus.NOT_FOUND, "S3에 존재하지 않는 영상입니다."),
    STILL_EXIST_S3_VIDEO(409, HttpStatus.CONFLICT, "삭제되지 않은 S3 영상이 존재합니다,");



    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    InterviewExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage){
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
