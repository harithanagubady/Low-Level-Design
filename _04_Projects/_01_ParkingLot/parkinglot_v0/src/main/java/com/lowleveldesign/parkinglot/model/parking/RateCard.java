package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.ParkingSpotType;
import com.lowleveldesign.parkinglot.model.enums.PricingStrategyType;

import java.util.*;

public class RateCard {

    private RateCard () {

    }

    public static final Map<ParkingSpotType, Map<PricingStrategyType, Double>> RATE_MAP = new HashMap<>();

    static {
        Map<PricingStrategyType, Double> carParkingSpotPrices = new HashMap<>();
        carParkingSpotPrices.put(PricingStrategyType.HOURLY, 20.0);
        carParkingSpotPrices.put(PricingStrategyType.PER_MINUTE, 1.0);

        Map<PricingStrategyType, Double> motorBikeParkingSpotPrices = new HashMap<>();
        motorBikeParkingSpotPrices.put(PricingStrategyType.HOURLY, 10.0);
        motorBikeParkingSpotPrices.put(PricingStrategyType.PER_MINUTE, 0.5);

        Map<PricingStrategyType, Double> largeVehicleParkingSpotPrices = new HashMap<>();
        largeVehicleParkingSpotPrices.put(PricingStrategyType.HOURLY, 30.0);
        largeVehicleParkingSpotPrices.put(PricingStrategyType.PER_MINUTE, 1.5);

        RATE_MAP.put(ParkingSpotType.CAR, carParkingSpotPrices);
        RATE_MAP.put(ParkingSpotType.MOTORBIKE, motorBikeParkingSpotPrices);
        RATE_MAP.put(ParkingSpotType.LARGE, largeVehicleParkingSpotPrices);
    }
}

