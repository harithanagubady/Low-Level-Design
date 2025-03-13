package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots) {
        //Prioritise DEFAULT locations or else return entrance or exit spots
        return parkingSpots.stream().filter(ps -> ps.getLocationType().equals(LocationType.DEFAULT))
                .findFirst().or(() -> parkingSpots.stream().findFirst()).orElse(null);
    }
}
