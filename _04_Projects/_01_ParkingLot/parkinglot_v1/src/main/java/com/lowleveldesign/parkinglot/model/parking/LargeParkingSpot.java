package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class LargeParkingSpot extends ParkingSpot {
    public LargeParkingSpot(String parkingSpotNo, int[] distanceToNearestEntrance,
                            int[] distanceToNearestExit, int[] distanceToNearestLift) {
        super(parkingSpotNo, ParkingSpotType.LARGE, distanceToNearestEntrance, distanceToNearestExit, distanceToNearestLift);
    }

}
