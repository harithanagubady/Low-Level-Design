package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class MotorBikeParkingSpot extends ParkingSpot {
    public MotorBikeParkingSpot(String parkingSpotNo) {
        super(parkingSpotNo, ParkingSpotType.MOTORBIKE);
    }

    public MotorBikeParkingSpot(String parkingSpotNo, LocationType locationType) {
        super(parkingSpotNo, ParkingSpotType.MOTORBIKE, locationType);
    }
}
