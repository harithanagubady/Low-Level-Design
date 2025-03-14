package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.InvalidInputException;
import com.lowleveldesign.parkinglot.exception.ParkingSpotUnavailableException;
import com.lowleveldesign.parkinglot.model.display.DisplayBoard;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.enums.VehicleType;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;

import java.util.*;

public class ParkingLevel {

    public static final String PARKING_SPOT_CANNOT_BE_NULL = "Parking Spot cannot be null";
    public static final String PARKING_SPOT = "parkingSpot";
    private final int parkingLevel;
    private DisplayBoard displayBoard;
    @Getter
    private final Map<ParkingSpotType, Set<ParkingSpot>> availableParkingSpots;

    private final Map<String, ParkingSpot> allParkingSpots;

    //1, ParkingSpot1
    //2, ParkingSpot2

    //TwoWheeler, parkingspotIds
    //FourWheeler, parkingspotIds
    //id, parkingspot

    private final int capacity;
    private final int totalSpots;

    private int noOfSlotsAvailable;

    public ParkingLevel(int capacity, int level) {
        this.capacity = capacity;
        this.totalSpots = 0;
        this.parkingLevel = level;
        this.availableParkingSpots = new HashMap<>();
        allParkingSpots = new HashMap<>();
        this.displayBoard = new DisplayBoard(parkingLevel);
    }

    protected void updateDisplayBoard(ParkingSpotType parkingSpotType, int size) {
        HashMap<ParkingSpotType, Integer> availableSpotsCount = displayBoard.getAvailableSpots();
        int oldSize = availableSpotsCount.getOrDefault(parkingSpotType, 0);
        availableSpotsCount.put(parkingSpotType, size);
        displayBoard.update(availableSpotsCount);
        ParkingLot.getInstance().updateMainDisplay(parkingSpotType, size - oldSize);
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (totalSpots >= this.capacity) {
            throw new IllegalStateException("Cannot add new spot. Parking lot is at full capacity");
        }
        validateObject(parkingSpot);
        parkingSpot.setParkingLevel(this);
        availableParkingSpots.putIfAbsent(parkingSpot.getParkingSpotType(), new HashSet<>());
        boolean added = availableParkingSpots.get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        if (!added) throw new IllegalStateException("Parking Spot not added " + parkingSpot.getSpotNumber());
        allocateParkingSpotsToLocationType(parkingSpot);
        allParkingSpots.put(parkingSpot.getSpotNumber(), parkingSpot);
        updateDisplayBoard(parkingSpot.getParkingSpotType(),
                availableParkingSpots.get(parkingSpot.getParkingSpotType()).size());
        noOfSlotsAvailable++;
    }

    private void allocateParkingSpotsToLocationType(ParkingSpot parkingSpot) {
        int[] distanceToNearestLift = parkingSpot.getDistanceToNearestLift();
        int[] distanceToNearestEntrance = parkingSpot.getDistanceToNearestEntrance();
        int[] distanceToNearestExit = parkingSpot.getDistanceToNearestExit();
        fillSpots(parkingSpot, distanceToNearestLift, distanceToNearestEntrance, distanceToNearestExit);
    }

    private void fillSpots(ParkingSpot parkingSpot, int[] distanceToNearestLift, int[] distanceToNearestEntrance,
                           int[] distanceToNearestExit) {

        ParkingLot parkingLotObj = ParkingLot.getInstance();
        String nearestLift = String.valueOf(distanceToNearestLift[0]);
        PriorityQueue<ParkingSpot> pq = parkingLotObj.getLiftSpots().getOrDefault(nearestLift,
                new PriorityQueue<>(Comparator.comparingInt(a -> a.getDistanceToNearestLift()[1])));
        pq.add(parkingSpot);
        parkingLotObj.getLiftSpots().put(nearestLift, pq);

        String nearestEntrance = String.valueOf(distanceToNearestEntrance[0]);
        pq = parkingLotObj.getEntranceSpots().getOrDefault(nearestEntrance,
                new PriorityQueue<>(Comparator.comparingInt(a -> a.getDistanceToNearestEntrance()[1])));
        pq.add(parkingSpot);
        parkingLotObj.getEntranceSpots().put(nearestEntrance, pq);

        String nearestExit = String.valueOf(distanceToNearestExit[0]);
        pq = parkingLotObj.getExitSpots().getOrDefault(nearestExit,
                new PriorityQueue<>(Comparator.comparingInt(a -> a.getDistanceToNearestExit()[1])));
        pq.add(parkingSpot);
        parkingLotObj.getExitSpots().put(nearestExit, pq);
    }

    private void validateObject(ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, PARKING_SPOT, PARKING_SPOT_CANNOT_BE_NULL);
            throw new InvalidInputException(msg);
        }
    }

    public void removeParkingSpot(String spotId) {
        ParkingSpot parkingSpot = allParkingSpots.remove(spotId);
        if (parkingSpot == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, PARKING_SPOT, PARKING_SPOT_CANNOT_BE_NULL);
            throw new InvalidInputException(msg);
        }
        availableParkingSpots.get(parkingSpot.getParkingSpotType()).remove(parkingSpot);
        updateDisplayBoard(parkingSpot.getParkingSpotType(),
                availableParkingSpots.get(parkingSpot.getParkingSpotType()).size());
        noOfSlotsAvailable++;
    }

    public ParkingSpot getParkingSpotById(String spotId) {
        if (spotId == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "spotId", "spot Id cannot be null");
            throw new InvalidInputException(msg);
        }
        return allParkingSpots.get(spotId);
    }

    public boolean canPark(VehicleType vehicleType) {
        int parkingSpotsForVehicleAvailable = availableParkingSpots.get(vehicleType.getSuitableSpotType()) == null ? 0 :
                availableParkingSpots.get(vehicleType.getSuitableSpotType()).size();
        return parkingSpotsForVehicleAvailable > 0;
    }

    public ParkingSpot assignParkingSpot(Vehicle vehicle, ParkingPreferences preferences) {

        List<ParkingSpot> availableSpots = getAvailableParkingSpotsByVehicleType(vehicle.getVehicleType());

        ParkingSpot parkingSpot = preferences.getParkingStrategy().allocateParkingSpot(availableSpots, preferences.getNum());

        if (parkingSpot == null) {
            throw new ParkingSpotUnavailableException("The requested parking spot is unavailable");
        }
        parkingSpot.assignParkingSpot(vehicle);
        makeSpotUnavailable(parkingSpot);
        return parkingSpot;
    }

    public void vacateParkingSpot(ParkingSpot parkingSpot) {
        makeSpotAvailable(parkingSpot);
    }

    protected List<ParkingSpot> getAvailableParkingSpotsByVehicleType(VehicleType vehicleType) {

        Set<ParkingSpot> parkingSpots = getAvailableParkingSpots().get(vehicleType.getSuitableSpotType());
        List<ParkingSpot> finalList = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable()) finalList.add(spot);
        }
        return finalList;
    }

    protected void makeSpotUnavailable(ParkingSpot parkingSpot) {
        availableParkingSpots.get(parkingSpot.getParkingSpotType()).remove(parkingSpot);
        updateDisplayBoard(parkingSpot.getParkingSpotType(),
                availableParkingSpots.get(parkingSpot.getParkingSpotType()).size());
    }

    protected void makeSpotAvailable(ParkingSpot parkingSpot) {
        availableParkingSpots.get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        updateDisplayBoard(parkingSpot.getParkingSpotType(),
                availableParkingSpots.get(parkingSpot.getParkingSpotType()).size());
    }

    public boolean isAvailable() {
        return noOfSlotsAvailable > 0;
    }

    @Override
    public String toString() {
        return "ParkingLevel: {" +
                "\n\tparkingLevelId='" + parkingLevel + '\'' +
                ",\n\tparkingSpots=" + allParkingSpots.values() +
                "\n}";
    }
}
