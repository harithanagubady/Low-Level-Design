package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class LargeSpotCostCalculation extends CostCalculation {
    public LargeSpotCostCalculation(PricingStrategy pricingStrategy) {
        super(pricingStrategy);
    }

    @Override
    public double calculateCost(ParkingTicket ticket) {
        return pricingStrategy.calculatePrice(ticket, RateCard.RATE_MAP.get(ParkingSpotType.LARGE));
    }
}
