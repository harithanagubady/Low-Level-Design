package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.PricingStrategyType;

import java.util.Map;

public interface PricingStrategy {

    double calculatePrice (ParkingTicket ticket, Map<PricingStrategyType, Double> rates);
}
