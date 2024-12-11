package com.lowleveldesign.parkinglot.model.factory;

import com.lowleveldesign.parkinglot.model.parking.CarSpotCostCalculation;
import com.lowleveldesign.parkinglot.model.parking.CostCalculation;
import com.lowleveldesign.parkinglot.model.parking.LargeSpotCostCalculation;
import com.lowleveldesign.parkinglot.model.parking.MotorBikeSpotCostCalculation;
import com.lowleveldesign.parkinglot.model.parking.PricingStrategy;
import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.enums.VehicleType;

public class CostCalculationFactory {

    public static CostCalculation generateCostCalculationObject(VehicleType vehicleType,
                                                                PricingStrategy pricingStrategy) {
        ParkingSpotType parkingSpotType = vehicleType.getSuitableSpotType();
        switch (parkingSpotType) {
            case MOTORBIKE:
                return new MotorBikeSpotCostCalculation(pricingStrategy);
            case CAR:
                return new CarSpotCostCalculation(pricingStrategy);
            case LARGE:
            default:
                return new LargeSpotCostCalculation(pricingStrategy);
        }
    }
}
