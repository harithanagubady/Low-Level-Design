package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public abstract class ParkingSpot {
    private String spotNumber;
    private boolean available = true;

    private ParkingSpotType parkingSpotType;
    private int[] distanceToNearestEntrance;
    private int[] distanceToNearestLift;
    private int[] distanceToNearestExit;
    private Vehicle vehicle;
    private ParkingLevel parkingLevel;

    public ParkingSpot(String spotNumber, ParkingSpotType parkingSpotType, int[] distanceToNearestEntrance,
                       int[] distanceToNearestExit, int[] distanceToNearestLift) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
        this.distanceToNearestEntrance = distanceToNearestEntrance;
        this.distanceToNearestLift = distanceToNearestLift;
        this.distanceToNearestExit = distanceToNearestExit;
    }

    public void assignParkingSpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        setAvailable(false);
    }

    public void vacateParkingSpot() {
        this.vehicle = null;
        setAvailable(true);
        parkingLevel.vacateParkingSpot(this);
    }

    @Override
    public String toString() {
        return "\t\tParkingSpot: {" +
                "spotNumber='" + spotNumber + '\'' +
                ", \tavailable=" + available +
                ", \tparkingSpotType=" + parkingSpotType.name() +
                ", \tnearestLift=" + Arrays.toString(distanceToNearestLift) +
                ", \tnearestEntranceAt=" + Arrays.toString(distanceToNearestEntrance) +
                ", \tnearestExitAt=" + Arrays.toString(distanceToNearestExit) +
                ", \tvehicle=" + vehicle +
                "}";
    }
}
