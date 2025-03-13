package com.lowleveldesign.parkinglot.exception;


public class ParkingLotUnavailableException extends BusinessException {

    public static final String PARKING_LOT_UNAVAILABLE_EXCEPTION = "ParkingLotUnavailableException: %s ";

    public ParkingLotUnavailableException(String message) {
        super(message, ErrorConstants.PARKING_LOT_UNAVAILABLE_CODE);
    }

    @Override
    public String toString() {
        return String.format(PARKING_LOT_UNAVAILABLE_EXCEPTION, getMessage());
    }
}

