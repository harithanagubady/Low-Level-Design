package com.lowleveldesign.parkinglot.exception;

public class InvalidInputException extends ValidationException {


    public InvalidInputException(String message) {
        super(message, ErrorConstants.INVALID_INPUT_CODE);
    }
}
