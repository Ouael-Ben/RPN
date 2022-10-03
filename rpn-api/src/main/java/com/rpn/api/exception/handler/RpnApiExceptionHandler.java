package com.rpn.api.exception.handler;

import com.rpn.api.dto.ErrorDto;
import com.rpn.api.exception.RpnApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RpnApiExceptionHandler {

    @ExceptionHandler(RpnApiException.class)
    public ResponseEntity<ErrorDto> appBusinessException(RpnApiException rpnApiException) {
        final ErrorDto errorDto = new ErrorDto();
        errorDto.setError(rpnApiException.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> runtimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest().build();
    }
}
