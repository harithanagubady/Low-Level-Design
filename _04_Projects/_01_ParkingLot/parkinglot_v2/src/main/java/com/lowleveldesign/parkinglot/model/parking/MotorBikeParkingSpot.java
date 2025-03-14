package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class MotorBikeParkingSpot extends ParkingSpot {
    public MotorBikeParkingSpot(String parkingSpotNo, int[] distanceToNearestEntrance,
                                int[] distanceToNearestExit, int[] distanceToNearestLift) {
        super(parkingSpotNo, ParkingSpotType.MOTORBIKE, distanceToNearestEntrance, distanceToNearestExit, distanceToNearestLift);
    }

}
