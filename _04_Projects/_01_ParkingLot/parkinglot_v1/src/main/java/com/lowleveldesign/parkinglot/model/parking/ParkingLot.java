package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.InvalidInputException;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLot {

    private static final int CAPACITY = 4;
    private static ParkingLot INSTANCE;
    private String parkingLotId;
    @Getter private final List<ParkingLevel> parkingLevels;

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    private final List<Entrance> entrances;
    private final List<Exit> exits;
    private final int noOfLevels;

    private ParkingLot(int noOfLevels) {
        this.parkingLotId = UUID.randomUUID().toString();
        this.parkingLevels = new ArrayList<>();
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.noOfLevels = noOfLevels;
    }

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParkingLot(CAPACITY);
        }
        return INSTANCE;
    }

    public void addParkingSpot(ParkingSpot parkingSpot, ParkingLevel parkingLevel) {
        if (parkingLevels.size() >= this.noOfLevels) {
            throw new IllegalStateException("Cannot add new spot. Parking lot is at full capacity");
        }
        if (parkingLevel == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "parkingLevel", "parkingLevel object cannot be null");
            throw new InvalidInputException(msg);
        }
        parkingLevel.addParkingSpot(parkingSpot);
    }

    protected ParkingSpot assignParkingSpot(Vehicle vehicle, ParkingStrategy parkingStrategy) {

        Optional<ParkingSpot> parkingSpotOptional = parkingLevels.stream()
                .filter(pl -> pl.canPark(vehicle.getVehicleType()))
                .map(pl -> pl.assignParkingSpot(vehicle, parkingStrategy))
                .filter(Objects::nonNull)
                .findFirst();
        return parkingSpotOptional.orElse(null);
    }

    public void addParkingLevel(ParkingLevel parkingLevel) {
        if (parkingLevels.size() >= this.noOfLevels) {
            throw new IllegalStateException("Cannot add new level. Parking lot is at full capacity");
        }
        if (parkingLevel == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "parkingLevel", "parkingLevel object cannot be null");
            throw new InvalidInputException(msg);
        }
        parkingLevels.add(parkingLevel);
    }

    protected void vacateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.vacateParkingSpot();
    }

    protected boolean isLotFull() {
        return parkingLevels.stream().noneMatch(ParkingLevel::isAvailable);
    }

    public void addEntrance(Entrance entrance) {
        if (entrance == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "entrance", "entrance object cannot be null");
            throw new InvalidInputException(msg);
        }
        entrances.add(entrance);
    }

    public void removeEntrance(String entranceId) {
        Entrance entrance = getEntrance(entranceId);
        if (entrance == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "entrance", "entrance object cannot be null");
            throw new InvalidInputException(msg);
        }
        entrances.remove(entrance);
    }

    public void addExit(Exit exit) {
        if (exit == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "exit", "exit object cannot be null");
            throw new InvalidInputException(msg);
        }
        exits.add(exit);
    }

    public void removeExit(String exitId) {
        Exit exit = getExit(exitId);
        if (exit == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "exit", "exit object cannot be null");
            throw new InvalidInputException(msg);
        }
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

    @Override
    public String toString() {

        String formattedEntrances = entrances.stream()
                .map(Entrance::toString) // Customize if needed
                .collect(Collectors.joining(",\n", "[\n", "\n\t]"));
        String formattedExits = exits.stream()
                .map(Exit::toString) // Customize if needed
                .collect(Collectors.joining(",\n", "[\n", "\n\t]"));
        return "ParkingLot: {" +
                "\n\tparkingLotId='" + parkingLotId + '\'' +
                ",\n\tparkingLevels=" + parkingLevels +
                ", \n\tentrances=" + formattedEntrances +
                ", \n\texits=" + formattedExits +
                "\n}";
    }
}
