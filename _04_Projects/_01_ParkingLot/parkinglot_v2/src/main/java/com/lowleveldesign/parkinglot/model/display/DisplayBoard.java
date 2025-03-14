package com.lowleveldesign.parkinglot.model.display;

import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.parking.ParkingSpot;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class DisplayBoard {

    @Getter private final int level;
    @Getter HashMap<ParkingSpotType, Integer> availableSpots;

    public DisplayBoard (int level) {
        this.level = level;
        this.availableSpots = new HashMap<>();
    }

    public void update(HashMap<ParkingSpotType, Integer> availableSpotsCount) {
        this.availableSpots = availableSpotsCount;
        printDisplayBoard();
    }

    public void printDisplayBoard() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Display Board - level " + this.getLevel() + " : \n" + this.getAvailableSpots();
    }
}
