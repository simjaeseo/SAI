package com.ssafy.sai.domain.schedule.exception;

import com.ssafy.sai.global.exception.BaseException;
import com.ssafy.sai.global.exception.BaseExceptionType;

public class ScheduleException extends BaseException {

    private BaseExceptionType exceptionType;

    public ScheduleException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
