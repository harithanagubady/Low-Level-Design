package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.InvalidInputException;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;

import java.util.*;

public class ParkingLot {

    private static final int LEVELS = 4;
    private static ParkingLot INSTANCE;
    private String parkingLotId;
    @Getter private final List<ParkingLevel> parkingLevels;
    @Getter private final Map<String, PriorityQueue<ParkingSpot>> entranceSpots;
    @Getter private final Map<String, PriorityQueue<ParkingSpot>> exitSpots;
    @Getter private final Map<String, PriorityQueue<ParkingSpot>> liftSpots;


    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    private final List<String> entranceGates;
    private final List<String> exitGates;
    private final List<String> lifts;

    private ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString();
        this.parkingLevels = new ArrayList<>();
        this.entranceGates = new ArrayList<>();
        this.exitGates = new ArrayList<>();
        this.lifts = new ArrayList<>();
        this.entranceSpots = new HashMap<>();
        this.exitSpots = new HashMap<>();
        this.liftSpots = new HashMap<>();
    }

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParkingLot();
        }
        return INSTANCE;
    }

    public void addParkingSpot(ParkingSpot parkingSpot, ParkingLevel parkingLevel) {
        if (parkingLevel == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "parkingLevel", "parkingLevel object cannot be null");
            throw new InvalidInputException(msg);
        }
        parkingLevel.addParkingSpot(parkingSpot);
    }

    public ParkingSpot assignParkingSpot(Vehicle vehicle, ParkingPreferences preferences) {

        Optional<ParkingSpot> parkingSpotOptional = parkingLevels.stream()
                .filter(pl -> pl.canPark(vehicle.getVehicleType()))
                .map(pl -> pl.assignParkingSpot(vehicle, preferences))
                .filter(Objects::nonNull)
                .findFirst();
        return parkingSpotOptional.orElse(null);
    }

    public void addParkingLevel(ParkingLevel parkingLevel) {
        if (parkingLevels.size() >= LEVELS) {
            throw new IllegalStateException("Cannot add new level. Parking lot is at full capacity");
        }
        if (parkingLevel == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "parkingLevel", "parkingLevel object cannot be null");
            throw new InvalidInputException(msg);
        }
        parkingLevels.add(parkingLevel);
    }

    public void vacateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.vacateParkingSpot();
    }

    protected boolean isLotFull() {
        return parkingLevels.stream().noneMatch(ParkingLevel::isAvailable);
    }

    public void addEntranceGate(String entranceGateNo) {
        entranceGates.add(entranceGateNo);
    }

    public void removeEntrance(String entranceGateNo) {
        entranceGates.remove(entranceGateNo);
    }

    public void addExit(String exitGateNo) {
        exitGates.add(exitGateNo);
    }

    public void removeExit(String exitGateNo) {
        exitGates.remove(exitGateNo);
    }


    public void addLift(String liftNo) {
        lifts.add(liftNo);
    }

    public void removeLift(String liftNo) {
        lifts.remove(liftNo);
    }

    @Override
    public String toString() {

        return "ParkingLot: {" +
                "\n\tparkingLotId='" + parkingLotId + '\'' +
                ",\n\tparkingLevels=" + parkingLevels +
                ", \n\tentrances=" + entranceGates +
                ", \n\texits=" + exitGates +
                ", \n\tlifts=" + lifts +
                "\n}";
    }

}
