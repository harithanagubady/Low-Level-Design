package com.lowleveldesign.parkinglot.model.vehicle;

import com.lowleveldesign.parkinglot.model.enums.VehicleType;

public class MotorBike extends Vehicle {

    public MotorBike(String licensePlateNumber) {
        super(VehicleType.MOTORBIKE, licensePlateNumber);
    }
}
