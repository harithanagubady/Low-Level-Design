package com.lowleveldesign.parkinglot.exception;

public class ParkingLotFullException extends BusinessException {

    public ParkingLotFullException(String message) {
        super(message, ErrorConstants.PARKING_LOT_FULL_CODE);
    }
}
