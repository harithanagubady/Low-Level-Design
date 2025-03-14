package com.lowleveldesign.parkinglot.model.parking;

import lombok.Getter;

@Getter
public class ParkingPreferences {

    ParkingStrategy parkingStrategy;
    String num;

    public ParkingPreferences (ParkingStrategy parkingStrategy, String num) {
        this.parkingStrategy = parkingStrategy;
        this.num = num;
    }
}
