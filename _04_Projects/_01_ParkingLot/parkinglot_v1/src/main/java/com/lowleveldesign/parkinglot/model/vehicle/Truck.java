package com.lowleveldesign.parkinglot.model.vehicle;

import com.lowleveldesign.parkinglot.model.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck (String licensePlateNumber) {
        super(VehicleType.TRUCK, licensePlateNumber);
    }
}
