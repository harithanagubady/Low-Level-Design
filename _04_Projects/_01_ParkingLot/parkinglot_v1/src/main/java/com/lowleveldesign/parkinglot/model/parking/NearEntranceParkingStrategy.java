package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.VehicleType;

import java.util.List;
import java.util.Objects;

public class NearEntranceParkingStrategy implements ParkingStrategy {

    private final LocationType locationType;

    public NearEntranceParkingStrategy() {
        this.locationType = LocationType.NEAR_ENTRANCE;
    }

    @Override
    public ParkingSpot allocateParkingSpot(VehicleType vehicleType, List<ParkingSpot> parkingSpots) {
        return parkingSpots.stream()
                .filter(parkingSpot -> parkingSpot.isAvailable()
                        && parkingSpot.getParkingSpotType().isSuitableFor(vehicleType)
                        && Objects.equals(parkingSpot.getLocationType(), locationType))
                .findFirst().orElse(null);
    }
}
