package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class LargeParkingSpot extends ParkingSpot {
    public LargeParkingSpot(String parkingSpotNo) {
        super(parkingSpotNo, ParkingSpotType.LARGE);
    }

    public LargeParkingSpot(String parkingSpotNo, LocationType locationType) {
        super(parkingSpotNo, ParkingSpotType.LARGE, locationType);
    }
}
