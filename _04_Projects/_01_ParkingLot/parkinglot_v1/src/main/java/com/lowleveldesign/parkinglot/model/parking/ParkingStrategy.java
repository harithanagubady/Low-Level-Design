package com.lowleveldesign.parkinglot.model.parking;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot allocateParkingSpot (List<ParkingSpot> parkingSpots);
}
