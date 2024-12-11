package com.lowleveldesign.parkinglot.model.enums;

import lombok.Getter;

@Getter
public enum VehicleType {

    CAR,//("car"),
    TRUCK,//("truck"),
    ELECTRIC,//("electric"),
    VAN,//("van"),
    MOTORBIKE,//("motor bike"),
    EBIKE;//("electric bike");

//    private String description;
//
//    VehicleType(String description) {
//        this.description = description;
//    }

    public boolean canFitInSpot(ParkingSpotType spotType) {
        switch (this) {
            case TRUCK:
            case VAN:
                return spotType == ParkingSpotType.LARGE;
            case MOTORBIKE:
                return spotType == ParkingSpotType.MOTORBIKE;
            case CAR:
                return spotType == ParkingSpotType.CAR;
            case ELECTRIC:
                return spotType == ParkingSpotType.ELECTRIC;
            default:
                return false;
        }
    }


    public ParkingSpotType getSuitableSpotType() {
        switch (this) {
            case MOTORBIKE:
                return ParkingSpotType.MOTORBIKE;
            case CAR:
                return ParkingSpotType.CAR;
            case ELECTRIC:
                return ParkingSpotType.ELECTRIC;
            case VAN:
            case TRUCK:
            default:
                return ParkingSpotType.LARGE;
        }
    }
}
