package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ParkingSpot {
    private String spotNumber;
    private boolean available = true;

    private ParkingSpotType parkingSpotType;
    private LocationType locationType;
    private Vehicle vehicle;

    public ParkingSpot(String spotNumber, ParkingSpotType parkingSpotType) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
        this.locationType = LocationType.DEFAULT;
    }

    public ParkingSpot(String spotNumber, ParkingSpotType parkingSpotType, LocationType locationType) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
        this.locationType = locationType;
    }

    public void assignParkingSpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        setAvailable(false);
    }

    public void vacateParkingSpot() {
        this.vehicle = null;
        setAvailable(true);
    }

    @Override
    public String toString() {
        return "\t\tParkingSpot: {" +
                "spotNumber='" + spotNumber + '\'' +
                ", \tavailable=" + available +
                ", \tparkingSpotType=" + parkingSpotType.name() +
                ", \tlocationType=" + locationType.name() +
                ", \tvehicle=" + vehicle +
                "}";
    }
}
