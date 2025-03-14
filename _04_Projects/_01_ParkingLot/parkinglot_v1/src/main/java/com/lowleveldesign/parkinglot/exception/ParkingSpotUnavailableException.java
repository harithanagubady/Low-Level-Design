package com.lowleveldesign.parkinglot.exception;


public class ParkingSpotUnavailableException extends BusinessException {

    public static final String PARKING_SPOT_UNAVAILABLE_EXCEPTION = "ParkingSpotUnavailableException: %s ";

    public ParkingSpotUnavailableException(String message) {
        super(message, ErrorConstants.PARKING_SPOT_UNAVAILABLE_CODE);
    }

    @Override
    public String toString() {
        return String.format(PARKING_SPOT_UNAVAILABLE_EXCEPTION, getMessage());
    }
}

