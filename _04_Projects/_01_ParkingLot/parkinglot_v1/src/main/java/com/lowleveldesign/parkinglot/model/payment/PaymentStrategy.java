package com.lowleveldesign.parkinglot.model.payment;

public interface PaymentStrategy {

    void processPayment (double amount);
}
