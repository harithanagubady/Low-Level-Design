package com.lowleveldesign.parkinglot.model.vehicle;

import com.lowleveldesign.parkinglot.model.enums.VehicleType;

public class Car extends Vehicle{

    public Car (String licensePlateNumber) {
        super(VehicleType.CAR, licensePlateNumber);
    }
}
