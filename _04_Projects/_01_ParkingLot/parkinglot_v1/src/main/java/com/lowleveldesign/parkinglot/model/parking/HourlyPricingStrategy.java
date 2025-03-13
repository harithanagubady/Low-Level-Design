package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.PricingStrategyType;

import java.time.Duration;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {

    private final PricingStrategyType hourlyStrategyType;

    public HourlyPricingStrategy() {
        hourlyStrategyType = PricingStrategyType.HOURLY;
    }

    @Override
    public double calculatePrice(ParkingTicket ticket, Map<PricingStrategyType, Double> vehiclePrices) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        long durationInMinutes = duration.toMinutes();
        double price = vehiclePrices.get(hourlyStrategyType);
        return Math.ceil(durationInMinutes / 60.0) * price;
    }
}
