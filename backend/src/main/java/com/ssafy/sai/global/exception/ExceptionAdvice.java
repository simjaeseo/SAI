package com.ssafy.sai.global.exception;

import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.schedule.exception.ScheduleException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handlerBaseException(BaseException exception) {
        log.error("BaseException errorMessage(): {}", exception.getExceptionType().getErrorMessage());
        log.error("BaseException errorCode(): {}", exception.getExceptionType().getErrorCode());

        return new ResponseEntity(new ExceptionDto(exception.getExceptionType().getErrorCode(), exception.getExceptionType().getHttpStatus(), exception.getExceptionType().getErrorMessage()), exception.getExceptionType().getHttpStatus());
    }

    // @Valid 에서 예외 발생
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleValidEx(BindException exception) {
        log.error("@ValidException 발생! {}", exception.getMessage());
        return new ResponseEntity(new ExceptionDto(400, HttpStatus.BAD_REQUEST, exception.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    // HttpMessageNotReadableException  => json 파싱 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableExceptionEx(HttpMessageNotReadableException exception) {
        log.error("Json을 파싱하는 과정에서 예외 발생! {}", exception.getMessage());
        return new ResponseEntity(new ExceptionDto(400, HttpStatus.BAD_REQUEST, "json을 파싱하는 과정에서 예외가 발생했습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullEx(Exception exception) {
        log.error("NullPointerException 발생! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(500, HttpStatus.INTERNAL_SERVER_ERROR, "null 값을 참조하여 예외가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity handleMemberEx(MemberException exception) {
        log.error("MemberExceptio 발생 ! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(exception.getExceptionType().getErrorCode(),
                exception.getExceptionType().getHttpStatus(),
                exception.getExceptionType().getErrorMessage()), exception.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity handleScheduleEx(ScheduleException exception) {
        log.error("Schedule Exception 발생 ! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(exception.getExceptionType().getErrorCode(),
                exception.getExceptionType().getHttpStatus(),
                exception.getExceptionType().getErrorMessage()),
                exception.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleMemberEx(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity(HttpStatus.OK);
    }

    //org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'DELETE' not supported

    @Data
    @AllArgsConstructor
    static class ExceptionDto {
        private Integer errorCode;
        private HttpStatus httpStatus;
        private String errorMessage;
    }
}
