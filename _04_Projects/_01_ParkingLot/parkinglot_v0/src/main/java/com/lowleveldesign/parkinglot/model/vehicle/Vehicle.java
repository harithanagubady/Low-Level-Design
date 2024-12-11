package com.lowleveldesign.parkinglot.model.vehicle;

import com.lowleveldesign.parkinglot.model.enums.VehicleType;
import lombok.Getter;

@Getter
public abstract class Vehicle {

    private String licensePlateNo;
    private final VehicleType vehicleType;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle(VehicleType vehicleType, String licensePlateNo) {
        this.vehicleType = vehicleType;
        this.licensePlateNo = licensePlateNo;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlateNo='" + licensePlateNo + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
