package com.lowleveldesign.parkinglot.model.enums;

import com.lowleveldesign.parkinglot.model.parking.ParkingSpot;
import lombok.Getter;

public enum ParkingSpotType {

    ABLED,//("ABLED"),
    CAR,//("CAR"),
    LARGE,//("LARGE"),
    MOTORBIKE,//("MOTORBIKE"),
    ELECTRIC,//("ELECTRIC"),
    EBIKE;//("EBIKE");
//
//    @Getter private String description;
//
//    ParkingSpotType (String description) {
//        this.description = description;
//    }
    public boolean isSuitableFor(VehicleType vehicleType) {
        return vehicleType.canFitInSpot(this);
    }
}
