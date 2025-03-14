package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class NearEntranceParkingStrategy implements ParkingStrategy {


    public NearEntranceParkingStrategy() {
    }

    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots, String num) {
        PriorityQueue<ParkingSpot> nearEntranceSpots = ParkingLot.getInstance().getEntranceSpots().get(num);
        return nearEntranceSpots.stream().filter(parkingSpots::contains).findFirst().orElse(null);
    }
}
