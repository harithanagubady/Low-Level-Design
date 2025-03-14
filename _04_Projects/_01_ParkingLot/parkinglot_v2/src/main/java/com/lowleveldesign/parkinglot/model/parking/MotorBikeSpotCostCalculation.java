package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class MotorBikeSpotCostCalculation extends CostCalculation {

    public MotorBikeSpotCostCalculation(PricingStrategy pricingStrategy) {
        super(pricingStrategy);
    }

    @Override
    public double calculateCost(ParkingTicket ticket) {
        return pricingStrategy.calculatePrice(ticket, RateCard.RATE_MAP.get(ParkingSpotType.MOTORBIKE));
    }
}
