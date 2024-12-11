package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class CarParkingSpot extends ParkingSpot {

    public CarParkingSpot(String parkingSpotNo) {
        super(parkingSpotNo, ParkingSpotType.CAR);
    }

    public CarParkingSpot(String parkingSpotNo, LocationType locationType) {
        super(parkingSpotNo, ParkingSpotType.CAR, locationType);
    }
}
