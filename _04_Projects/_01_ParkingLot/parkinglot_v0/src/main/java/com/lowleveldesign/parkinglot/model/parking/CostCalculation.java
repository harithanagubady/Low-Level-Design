package com.lowleveldesign.parkinglot.model.parking;

public abstract class CostCalculation {

    PricingStrategy pricingStrategy;

    public CostCalculation (PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public abstract double calculateCost (ParkingTicket ticket);
}
