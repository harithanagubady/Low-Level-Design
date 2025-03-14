package com.lowleveldesign.parkinglot.exception;

public class ValidationException extends RuntimeException {

    private final String errorCode;

    public ValidationException (String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ValidationException (String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
