package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.PricingStrategyType;

import java.time.Duration;
import java.util.Map;

public class PerMinutePricingStrategy implements PricingStrategy {

    private final PricingStrategyType perMinuteStrategyType = PricingStrategyType.PER_MINUTE;

    @Override
    public double calculatePrice(ParkingTicket ticket, Map<PricingStrategyType, Double> vehiclePrices) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        double price = vehiclePrices.get(perMinuteStrategyType);
        return Math.ceil(duration.toMinutes()) * price;
    }
}
