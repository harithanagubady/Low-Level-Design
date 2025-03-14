package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class NearLiftParkingStrategy implements ParkingStrategy {


    public NearLiftParkingStrategy() {
    }

    @Override
    public ParkingSpot allocateParkingSpot(List<ParkingSpot> parkingSpots, String num) {
        PriorityQueue<ParkingSpot> nearLiftSpots = ParkingLot.getInstance().getLiftSpots().get(num);
        return nearLiftSpots.stream().filter(parkingSpots::contains).findFirst().orElse(null);
    }
}
