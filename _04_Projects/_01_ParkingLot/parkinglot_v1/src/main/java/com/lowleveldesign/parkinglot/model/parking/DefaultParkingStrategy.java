package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots, String num) {
        return parkingSpots.stream().findFirst().orElse(null);
    }
}
