package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.VehicleType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot allocateParkingSpot(VehicleType vehicleType, List<ParkingSpot> parkingSpots) {
        List<ParkingSpot> parkingSpots1 = parkingSpots.stream()
                .filter(parkingSpot -> parkingSpot.getParkingSpotType().isSuitableFor(vehicleType)
                        && parkingSpot.isAvailable()).collect(Collectors.toList());
        //Prioritise DEFAULT locations or else return entrance or exit spots
        return parkingSpots1.stream().filter(ps -> ps.getLocationType().equals(LocationType.DEFAULT))
                .findFirst().or(() -> parkingSpots1.stream().findFirst()).orElse(null);
    }
}
