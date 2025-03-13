package com.lowleveldesign.parkinglot.model.payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processed Credit Card payment of Rs." + amount + " successfully");
    }
}
