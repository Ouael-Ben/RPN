package com.rpn.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RpnApiException extends RuntimeException{
    private String message;
}
