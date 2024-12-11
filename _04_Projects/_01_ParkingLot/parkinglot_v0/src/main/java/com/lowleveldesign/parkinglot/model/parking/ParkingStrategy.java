package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.VehicleType;

import java.util.List;

public interface ParkingStrategy {

    ParkingSpot allocateParkingSpot (VehicleType vehicleType, List<ParkingSpot> parkingSpots);
}
