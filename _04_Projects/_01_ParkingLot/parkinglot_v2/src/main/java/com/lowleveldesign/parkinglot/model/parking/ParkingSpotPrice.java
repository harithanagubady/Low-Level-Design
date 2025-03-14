package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.PricingStrategyType;
import lombok.Getter;

@Getter
public class ParkingSpotPrice {

    private PricingStrategyType pricingStrategyType;
    private double price;

    public ParkingSpotPrice(PricingStrategyType pricingStrategyType, double price) {
        this.pricingStrategyType = pricingStrategyType;
        this.price = price;
    }
}