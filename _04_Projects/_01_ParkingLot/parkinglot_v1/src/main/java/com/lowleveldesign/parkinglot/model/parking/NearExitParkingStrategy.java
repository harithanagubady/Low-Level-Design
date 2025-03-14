package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;

import java.util.List;
import java.util.PriorityQueue;

public class NearExitParkingStrategy implements ParkingStrategy {


    public NearExitParkingStrategy () {
    }

    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots, String num) {
        PriorityQueue<ParkingSpot> nearExitSpots = ParkingLot.getInstance().getExitSpots().get(num);
        return nearExitSpots.stream().filter(parkingSpots::contains).findFirst().orElse(null);
    }
}
