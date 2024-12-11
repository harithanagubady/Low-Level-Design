package com.lowleveldesign.parkinglot.model.vehicle;

import com.lowleveldesign.parkinglot.model.enums.VehicleType;

public class MotorCycle extends Vehicle {
    public MotorCycle() {
        super(VehicleType.MOTORBIKE);
    }

    public MotorCycle (String licensePlateNumber) {
        super(VehicleType.MOTORBIKE, licensePlateNumber);
    }
}
