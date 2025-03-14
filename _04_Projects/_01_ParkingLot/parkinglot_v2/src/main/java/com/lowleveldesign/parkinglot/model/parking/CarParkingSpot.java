package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class CarParkingSpot extends ParkingSpot {

    public CarParkingSpot(String parkingSpotNo, int[] distanceToNearestEntrance,
                          int[] distanceToNearestExit, int[] distanceToNearestLift) {
        super(parkingSpotNo, ParkingSpotType.CAR, distanceToNearestEntrance, distanceToNearestExit, distanceToNearestLift);
    }

}
