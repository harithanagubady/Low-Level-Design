package com.lowleveldesign.parkinglot.exception;

public class BusinessException extends RuntimeException {

    private final String errorCode;

    public BusinessException (String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException (String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
