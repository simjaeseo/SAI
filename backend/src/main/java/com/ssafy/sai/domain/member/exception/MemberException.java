package com.ssafy.sai.domain.member.exception;

import com.ssafy.sai.global.exception.BaseException;
import com.ssafy.sai.global.exception.BaseExceptionType;

public class MemberException extends BaseException {

    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
