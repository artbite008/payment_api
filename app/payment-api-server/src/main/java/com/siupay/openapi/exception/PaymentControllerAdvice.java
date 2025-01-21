package com.siupay.openapi.exception;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.siupay.common.api.dto.response.GenericResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class PaymentControllerAdvice {


    public static final String EXCEPTION_MESSAGE = "catch global exception with url:{}";

    @ExceptionHandler
    public ResponseEntity handlerException(HttpServletRequest req, Throwable ex) {
        logError(req, ex);
        return new ResponseEntity<>(GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMsg()), null, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlerException(HttpServletRequest req, PaymentException ex) {
        logError(req, ex);
        if (Objects.nonNull(ex.getErrorCode())) {
            // 拦截成功，打印原始异常
            log.error("PaymentExceptionHandler error code {} , msg {}", ex.getErrorCode().getCode(),
                    ex.getErrorCode().getMsg());
            return new ResponseEntity<>(GenericResponse.fail(ex.getErrorCode().getCode(),
                     ex.getErrorCode().getMsg()),
                    null, HttpStatus.OK);
        } else {// 拦截失败，将原始异常包装到GenericResult返回
            return new ResponseEntity<>(GenericResponse.fail(ex.getCode(), ex.getMessage()), null, HttpStatus.OK);
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handlerException(HttpServletRequest req, DataAccessException ex) {
        logError(req, ex);
        return new ResponseEntity<>(GenericResponse.fail(ErrorCode.DB_EXCEPTION.getCode(), ErrorCode.DB_EXCEPTION.getMsg()), null, HttpStatus.OK);
    }


    private void logError(HttpServletRequest req, Throwable ex) {
        String requestURI = req.getRequestURI();
        log.error(EXCEPTION_MESSAGE, requestURI, ex);
    }
}
