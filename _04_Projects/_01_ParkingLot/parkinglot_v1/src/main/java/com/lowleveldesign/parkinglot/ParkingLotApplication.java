package com.lowleveldesign.parkinglot;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.parking.*;
import com.lowleveldesign.parkinglot.model.payment.CashPaymentStrategy;
import com.lowleveldesign.parkinglot.model.payment.CreditCardPaymentStrategy;
import com.lowleveldesign.parkinglot.model.vehicle.Car;
import com.lowleveldesign.parkinglot.model.vehicle.MotorBike;
import com.lowleveldesign.parkinglot.model.vehicle.Truck;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ParkingLotApplication {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingLevel parkingLevel1 = new ParkingLevel(10);
        parkingLot.addParkingLevel(parkingLevel1);

        ParkingSpot carParkingSpot1 = new CarParkingSpot("CarSpot1");
        ParkingSpot carParkingSpot2 = new CarParkingSpot("CarSpot2", LocationType.NEAR_ENTRANCE);
        ParkingSpot largeParkingSpot1 = new LargeParkingSpot("LargeSpot1", LocationType.NEAR_LIFT);
        ParkingSpot largeParkingSpot2 = new LargeParkingSpot("LargeSpot2", LocationType.NEAR_ENTRANCE);

        parkingLot.addParkingSpot(carParkingSpot1, parkingLevel1);
        parkingLot.addParkingSpot(carParkingSpot2, parkingLevel1);
        parkingLot.addParkingSpot(largeParkingSpot1, parkingLevel1);
        parkingLot.addParkingSpot(largeParkingSpot2, parkingLevel1);


        ParkingLevel parkingLevel2 = new ParkingLevel(10);
        ParkingSpot motorBikeParkingSpot1 = new MotorBikeParkingSpot("MotorBikeSpot1");
        ParkingSpot motorBikeParkingSpot2 = new MotorBikeParkingSpot("MotorBikeSpot2");
        parkingLevel2.addParkingSpot(motorBikeParkingSpot1);
        parkingLevel2.addParkingSpot(motorBikeParkingSpot2);
        parkingLot.addParkingLevel(parkingLevel2);

        Entrance entrance = new Entrance("E1");
        parkingLot.addEntrance(entrance);

        Exit exit = new Exit("Ex1");
        parkingLot.addExit(exit);

        System.out.println(parkingLot);
        Map<String, ParkingTicket> tickets = new HashMap<>();

        /*
        Commands:
        issueTicket car1
        setParkingStrategy NearEntrance
        issueTicket car2
        setParkingStrategy NearLift
        issueTicket motorBike1//Unavailable
        setParkingStrategy Default
        issueTicket motorBike1
        issueTicket motorBike2
        issueTicket truck1
        setParkingStrategy NearEntrance
        issueTicket truck2
        issueTicket car1//Full
        scanAndVacate e03492b0-af64-4637-ad51-b5d4352ed451
        setPricingStrategy PerMinute
        setPaymentStrategy CreditCard
        scanAndVacate 1dce98f6-25cd-4756-bd2b-3328eb93a8fb
         */
        while (true) {
            String input = scn.nextLine();
            input = input.trim();
            String[] inpArg = input.split(" ");

            try {
                ParkingTicket ticket;
                switch (inpArg[0]) {
                    case "issueTicket": {
                        Vehicle v = getVehicle(inpArg[1]);
                        ticket = entrance.issueTicket(v);
                        tickets.put(ticket.getTicketNo(), ticket);
                    }
                    break;

                    case "setPricingStrategy": {

                        inpArg[1] = inpArg[1].trim();
                        switch (inpArg[1]) {
                            case "Hourly":
                                exit.setPricingStrategy(new HourlyPricingStrategy());
                                break;
                            case "PerMinute":
                                exit.setPricingStrategy(new PerMinutePricingStrategy());
                                break;
                        }
                    }
                    break;
                    case "setParkingStrategy": {

                        inpArg[1] = inpArg[1].trim();
                        switch (inpArg[1]) {
                            case "NearEntrance":
                                entrance.setParkingStrategy(new NearEntranceParkingStrategy());
                                break;
                            case "NearLift":
                                entrance.setParkingStrategy(new NearLiftParkingStrategy());
                                break;
                            case "Default":
                                entrance.setParkingStrategy(new DefaultParkingStrategy());
                                break;
                            default:
                                throw new IllegalArgumentException("parkingStrategy is not provided");
                        }
                    }
                    break;
                    case "setPaymentStrategy": {

                        inpArg[1] = inpArg[1].trim();
                        switch (inpArg[1]) {
                            case "Cash":
                                exit.setPaymentStrategy(new CashPaymentStrategy());
                                break;
                            case "CreditCard":
                                exit.setPaymentStrategy(new CreditCardPaymentStrategy());
                                break;
                            default:
                                throw new IllegalArgumentException("paymentStrategy is not provided");
                        }
                    }
                    break;
                    case "scanAndVacate": {
                        inpArg[1] = inpArg[1].trim();
                        exit.scanAndVacate(tickets.get(inpArg[1]));
                    }
                    break;
                    default:
                        System.out.println("Command invalid");
                }

                System.out.println(parkingLot);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Vehicle getVehicle(String input) {
        Map<String, Vehicle> vehicles = new HashMap<>();
        Vehicle car1 = new Car("C1");
        vehicles.put("car1", car1);
        Vehicle truck1 = new Truck("T1");
        vehicles.put("truck1", truck1);
        Vehicle car2 = new Car("C2");
        vehicles.put("car2", car2);
        Vehicle car3 = new Car("C3");
        vehicles.put("car3", car3);
        Vehicle truck2 = new Truck("T2");
        vehicles.put("truck2", truck2);
        Vehicle truck3 = new Truck("T3");
        vehicles.put("truck3", truck3);
        Vehicle motorBike1 = new MotorBike("M1");
        vehicles.put("motorBike1", motorBike1);
        Vehicle motorBike2 = new MotorBike("M2");
        vehicles.put("motorBike2", motorBike2);

        return vehicles.get(input);
    }
}
