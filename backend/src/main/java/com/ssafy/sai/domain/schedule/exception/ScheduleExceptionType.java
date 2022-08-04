package com.ssafy.sai.domain.schedule.exception;

import com.ssafy.sai.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ScheduleExceptionType implements BaseExceptionType {

    NOT_FOUND_SCHEDULE(404,HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    ScheduleExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }


    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
