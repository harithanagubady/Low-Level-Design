package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;

import java.util.List;
import java.util.Objects;

public class NearLiftParkingStrategy implements ParkingStrategy {

    private final LocationType locationType;

    public NearLiftParkingStrategy() {
        this.locationType = LocationType.NEAR_LIFT;
    }

    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots) {
        return parkingSpots.stream()
                .filter(parkingSpot -> Objects.equals(parkingSpot.getLocationType(), locationType))
                .findFirst().orElse(null);
    }
}
