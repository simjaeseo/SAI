package com.ssafy.sai.domain.interview.exception;

import com.ssafy.sai.global.exception.BaseException;
import com.ssafy.sai.global.exception.BaseExceptionType;

public class InterviewException extends BaseException {

    private BaseExceptionType exceptionType;

    public InterviewException(BaseExceptionType exceptionType){
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
