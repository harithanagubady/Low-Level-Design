package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;

public class CarSpotCostCalculation extends CostCalculation {


    public CarSpotCostCalculation(PricingStrategy pricingStrategy) {
        super(pricingStrategy);
    }

    @Override
    public double calculateCost(ParkingTicket ticket) {
        return pricingStrategy.calculatePrice(ticket, RateCard.RATE_MAP.get(ParkingSpotType.CAR));
    }
}
