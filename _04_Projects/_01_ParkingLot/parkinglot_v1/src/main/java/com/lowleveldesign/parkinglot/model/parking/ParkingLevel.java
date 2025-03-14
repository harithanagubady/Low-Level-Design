package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.InvalidInputException;
import com.lowleveldesign.parkinglot.exception.ParkingSpotUnavailableException;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.enums.VehicleType;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;

import java.util.*;

public class ParkingLevel {

    public static final String PARKING_SPOT_CANNOT_BE_NULL = "Parking Spot cannot be null";
    public static final String PARKING_SPOT = "parkingSpot";
    private final String parkingLevelId;

    @Getter
    private final Map<ParkingSpotType, DoublyLinkedList<ParkingSpot>> availableParkingSpots;

    private final Map<String, DoublyLinkedList.DLLNode<ParkingSpot>> allParkingSpotsById;

    //1, ParkingSpot1
    //2, ParkingSpot2

    //TwoWheeler, parkingspotIds
    //FourWheeler, parkingspotIds
    //id, parkingspot

    private final int capacity;

    private int noOfSlotsAvailable;

    public ParkingLevel(int capacity) {
        this.capacity = capacity;
        this.parkingLevelId = UUID.randomUUID().toString();
        this.allParkingSpotsById = new HashMap<>();
        this.availableParkingSpots = new HashMap<>();
    }


    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (allParkingSpotsById.size() >= this.capacity) {
            throw new IllegalStateException("Cannot add new spot. Parking lot is at full capacity");
        }
        validateObject(parkingSpot);
        parkingSpot.setParkingLevel(this);
        availableParkingSpots.putIfAbsent(parkingSpot.getParkingSpotType(), new DoublyLinkedList<>());
        DoublyLinkedList.DLLNode<ParkingSpot> parkingSpotNode = availableParkingSpots.get(parkingSpot.getParkingSpotType()).insert(parkingSpot);
        allParkingSpotsById.put(parkingSpot.getSpotNumber(), parkingSpotNode);
        noOfSlotsAvailable++;
    }

    private void validateObject(ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, PARKING_SPOT, PARKING_SPOT_CANNOT_BE_NULL);
            throw new InvalidInputException(msg);
        }
    }

    public void removeParkingSpot(String spotId) {
        DoublyLinkedList.DLLNode<ParkingSpot> parkingSpotNode = allParkingSpotsById.remove(spotId);
        if (parkingSpotNode == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, PARKING_SPOT, PARKING_SPOT_CANNOT_BE_NULL);
            throw new InvalidInputException(msg);
        }
        availableParkingSpots.get(parkingSpotNode.parkingSpot.getParkingSpotType()).remove(parkingSpotNode);
        noOfSlotsAvailable++;
    }

    public DoublyLinkedList.DLLNode<ParkingSpot> getParkingSpotById(String spotId) {
        if (spotId == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "spotId", "spot Id cannot be null");
            throw new InvalidInputException(msg);
        }
        return allParkingSpotsById.get(spotId);
    }

    public boolean canPark(VehicleType vehicleType) {
        int parkingSpotsForVehicleAvailable = availableParkingSpots.get(vehicleType.getSuitableSpotType()) == null ? 0 :
                availableParkingSpots.get(vehicleType.getSuitableSpotType()).size;
        return parkingSpotsForVehicleAvailable > 0;
    }

    public ParkingSpot assignParkingSpot(Vehicle vehicle, ParkingStrategy parkingStrategy) {

        List<ParkingSpot> availableSpots = getAvailableParkingSpotsByVehicleType(vehicle.getVehicleType());
        ParkingSpot parkingSpot = parkingStrategy.allocateParkingSpot(availableSpots);
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

        DoublyLinkedList<ParkingSpot> parkingSpots = getAvailableParkingSpots()
                .get(vehicleType.getSuitableSpotType());
        List<ParkingSpot> finalList = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable()) finalList.add(spot);
        }
        return finalList;
    }

    protected void makeSpotUnavailable(ParkingSpot parkingSpot) {
        DoublyLinkedList.DLLNode<ParkingSpot> parkingSpotDLLNode = getParkingSpotById(parkingSpot.getSpotNumber());
        availableParkingSpots.get(parkingSpot.getParkingSpotType()).remove(parkingSpotDLLNode);
    }

    protected void makeSpotAvailable(ParkingSpot parkingSpot) {
        availableParkingSpots.get(parkingSpot.getParkingSpotType()).insert(parkingSpot);
    }

    public boolean isAvailable() {
        return noOfSlotsAvailable > 0;
    }

    @Override
    public String toString() {
        return "ParkingLevel: {" +
                "\n\tparkingLevelId='" + parkingLevelId + '\'' +
                ",\n\tparkingSpots=" + allParkingSpotsById.values() +
                "\n}";
    }
}

class DoublyLinkedList<ParkingSpot> implements Iterable<ParkingSpot> {

    DLLNode<ParkingSpot> head;
    DLLNode<ParkingSpot> tail;
    int size;

    public DoublyLinkedList() {
        this.size = 0;
    }

    @Override
    public Iterator<ParkingSpot> iterator() {
        return new DoublyLinkedListIterator();
    }

    class DoublyLinkedListIterator implements Iterator<ParkingSpot> {

        DLLNode<ParkingSpot> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public ParkingSpot next() {
            ParkingSpot data = current.parkingSpot;
            current = current.next;
            return data;
        }
    }

    static class DLLNode<ParkingSpot> {
        DLLNode<ParkingSpot> prev;
        DLLNode<ParkingSpot> next;
        ParkingSpot parkingSpot;

        public DLLNode(ParkingSpot parkingSpot) {
            this.parkingSpot = parkingSpot;
        }

        @Override
        public String toString() {
            return this.parkingSpot.toString();
        }
    }

    DLLNode<ParkingSpot> insert(ParkingSpot parkingSpot) {
        DLLNode<ParkingSpot> newNode = new DLLNode<>(parkingSpot);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return newNode;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = tail.next;
        size++;
        return newNode;
    }

    void remove(DLLNode<ParkingSpot> node) {
        if (node == null) return;
        if (node == head) {
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                node.next.prev = node.prev;
                head = node.next;
                node.next = null;
            }
            size--;
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        size--;
    }
}
