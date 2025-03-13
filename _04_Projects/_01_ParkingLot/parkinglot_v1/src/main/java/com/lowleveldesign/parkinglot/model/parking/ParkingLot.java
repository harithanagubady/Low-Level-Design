package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.InvalidInputException;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParkingLot {

    public static final String PARKING_SPOT_CANNOT_BE_NULL = "Parking Spot cannot be null";
    public static final String PARKING_SPOT = "parkingSpot";
    public static final ParkingLot INSTANCE = new ParkingLot();
    private String parkingLotId;
    private int capacity;
    @Getter private final List<ParkingSpot> parkingSpots;
    private final List<Entrance> entrances;
    private final List<Exit> exits;

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    private ParkingLot() {
        this.capacity = 10;
        this.parkingLotId = UUID.randomUUID().toString();
        this.parkingSpots = new ArrayList<>();
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    protected ParkingSpot assignParkingSpot(Vehicle vehicle, ParkingStrategy parkingStrategy) {

        ParkingSpot parkingSpot = parkingStrategy.allocateParkingSpot(vehicle.getVehicleType(), parkingSpots);
        if (parkingSpot == null) return null;
        parkingSpot.assignParkingSpot(vehicle);
        return parkingSpot;
    }

    protected void vacateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.vacateParkingSpot();
    }

    protected boolean isLotFull() {
        return parkingSpots.stream().noneMatch(ParkingSpot::isAvailable);
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (parkingSpots.size() >= this.capacity) {
            throw new IllegalStateException("Cannot add new spot. Parking lot is at full capacity");
        }
        validateObject(parkingSpot);
        parkingSpots.add(parkingSpot);
    }

    public void removeParkingSpot(String spotId) {
        ParkingSpot parkingSpot = getParkingSpot(spotId);
        validateObject(parkingSpot);
        parkingSpots.remove(parkingSpot);
    }

    public void addEntrance(Entrance entrance) {
        validateObject(entrance);
        entrances.add(entrance);
    }

    public void removeEntrance(String entranceId) {
        Entrance entrance = getEntrance(entranceId);
        validateObject(entrance);
        entrances.remove(entrance);
    }

    public void addExit(Exit exit) {
        validateObject(exit);
        exits.add(exit);
    }

    public void removeExit(String exitId) {
        Exit exit = getExit(exitId);
        validateObject(exitId);
        exits.remove(exit);
    }

    private Exit getExit(String exitId) {
        return exits.stream()
                .filter(exit -> StringUtils.equalsIgnoreCase(exit.getExitNumber(), exitId))
                .findFirst().orElse(null);
    }

    private Entrance getEntrance(String entranceId) {
        return entrances.stream()
                .filter(entrance -> StringUtils.equalsIgnoreCase(entrance.getEntranceNumber(), entranceId))
                .findFirst().orElse(null);
    }

    public ParkingSpot getParkingSpot(String spotId) {
        return parkingSpots.stream()
                .filter(parkingSpot -> StringUtils.equalsIgnoreCase(parkingSpot.getSpotNumber(), spotId))
                .findFirst().orElse(null);
    }


    private static void validateObject(Object obj) {
        if (obj == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, PARKING_SPOT, PARKING_SPOT_CANNOT_BE_NULL);
            throw new InvalidInputException(msg);
        }
    }

    @Override
    public String toString() {
        String formattedParkingSpots = parkingSpots.stream()
                .map(ParkingSpot::toString) // Customize if needed
                .collect(Collectors.joining(",\n", "[\n", "\n\t]"));
        String formattedEntrances = entrances.stream()
                .map(Entrance::toString) // Customize if needed
                .collect(Collectors.joining(",\n", "[\n", "\n\t]"));
        String formattedExits = exits.stream()
                .map(Exit::toString) // Customize if needed
                .collect(Collectors.joining(",\n", "[\n", "\n\t]"));
        return "ParkingLot: {" +
                "\n\tparkingLotId='" + parkingLotId + '\'' +
                ",\n\tparkingSpots=" + formattedParkingSpots +
                ", \n\tentrances=" + formattedEntrances +
                ", \n\texits=" + formattedExits +
                "\n}";
    }
}
