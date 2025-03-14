package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.InvalidInputException;
import com.lowleveldesign.parkinglot.model.enums.ParkingTicketStatus;
import com.lowleveldesign.parkinglot.model.factory.CostCalculationFactory;
import com.lowleveldesign.parkinglot.model.enums.VehicleType;
import com.lowleveldesign.parkinglot.model.payment.CashPaymentStrategy;
import com.lowleveldesign.parkinglot.model.payment.PaymentStrategy;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Exit {

    private final String exitNo;
    PricingStrategy pricingStrategy;
    PaymentStrategy paymentStrategy;
    CostCalculation costCalculation;

    public Exit(String exitNo) {
        this.exitNo = exitNo;
        this.pricingStrategy = new HourlyPricingStrategy();
        this.paymentStrategy = new CashPaymentStrategy();
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void scanAndVacate(ParkingTicket ticket) {

        if (ticket == null) {
            String msg = String.format(ErrorConstants.INVALID_INPUT_MSG, "ticket", "null");
            throw new InvalidInputException(msg);
        }
        ticket.setExitTime(LocalDateTime.now());
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        costCalculation = CostCalculationFactory
                .generateCostCalculationObject(vehicleType, pricingStrategy);
        double amount = costCalculation.calculateCost(ticket);
        ticket.setAmount(amount);
        paymentStrategy.processPayment(amount);
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        ParkingLot.getInstance().vacateParkingSpot(parkingSpot);
        ticket.setParkingSpot(parkingSpot);
        ticket.setParkingTicketStatus(ParkingTicketStatus.PAID);
        ticket.setExitNo(exitNo);
    }
}
