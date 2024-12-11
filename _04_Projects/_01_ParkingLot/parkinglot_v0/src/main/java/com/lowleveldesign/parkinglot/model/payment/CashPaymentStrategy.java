package com.lowleveldesign.parkinglot.model.payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processed cash payment of Rs." + amount + " successfully");
    }
}
