package com.lowleveldesign.parkinglot;

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

public class ParkingLotApplication {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingLevel parkingLevel1 = new ParkingLevel(10);
        parkingLot.addParkingLevel(parkingLevel1);

        ParkingSpot carParkingSpot1 = new CarParkingSpot("CarSpot1", new int[]{11, 1}, new int[]{11, 2}, new int[]{12, 1});
        ParkingSpot carParkingSpot2 = new CarParkingSpot("CarSpot2", new int[]{11, 2}, new int[]{11, 1}, new int[]{12, 2});
        ParkingSpot largeParkingSpot1 = new LargeParkingSpot("LargeSpot1", new int[]{13, 1}, new int[]{12, 1}, new int[]{11, 1});
        ParkingSpot largeParkingSpot2 = new LargeParkingSpot("LargeSpot2", new int[]{12, 1}, new int[]{12, 2}, new int[]{11, 2});

        parkingLot.addParkingSpot(carParkingSpot1, parkingLevel1);
        parkingLot.addParkingSpot(carParkingSpot2, parkingLevel1);
        parkingLot.addParkingSpot(largeParkingSpot1, parkingLevel1);
        parkingLot.addParkingSpot(largeParkingSpot2, parkingLevel1);

        ParkingLevel parkingLevel2 = new ParkingLevel(10);
        parkingLot.addParkingLevel(parkingLevel2);
        ParkingSpot motorBikeParkingSpot1 = new MotorBikeParkingSpot("MotorBikeSpot1", new int[]{21, 1}, new int[]{21, 1}, new int[]{22, 1});
        ParkingSpot motorBikeParkingSpot2 = new MotorBikeParkingSpot("MotorBikeSpot2", new int[]{21, 2}, new int[]{21, 2}, new int[]{21, 1});
        parkingLot.addParkingSpot(motorBikeParkingSpot1, parkingLevel2);
        parkingLot.addParkingSpot(motorBikeParkingSpot2, parkingLevel2);

        parkingLot.addEntranceGate("11");
        parkingLot.addEntranceGate("12");
        parkingLot.addEntranceGate("13");
        parkingLot.addEntranceGate("21");

        parkingLot.addExit("11");
        parkingLot.addExit("12");
        parkingLot.addExit("21");

        parkingLot.addLift("11");
        parkingLot.addLift("12");
        parkingLot.addLift("21");

        System.out.println(parkingLot);
        Map<String, ParkingTicket> tickets = new HashMap<>();

        /*
        Commands:
        issueTicket car1
        setParkingStrategy NearEntrance 11
        issueTicket car2
        setParkingStrategy NearLift 12
        issueTicket motorBike1//Unavailable
        setParkingStrategy Default -1
        issueTicket motorBike2
        issueTicket truck1
        setParkingStrategy NearEntrance 12
        issueTicket truck2
        issueTicket car3//Full
        scanAndVacate 7d3f945d-722c-4549-9520-5231bf3171a4
        setPricingStrategy PerMinute
        setPaymentStrategy CreditCard
        scanAndVacate c528ee6d-3143-4899-a7c6-f9b6cd8803ab
         */
        Entrance entrance = new Entrance("11");
        Exit exit = new Exit("12");

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
                        inpArg[2] = inpArg[2].trim();
                        switch (inpArg[1]) {
                            case "NearEntrance":
                                entrance.setParkingPreferences(new NearEntranceParkingStrategy(), inpArg[2]);
                                break;
                            case "NearLift":
                                entrance.setParkingPreferences(new NearLiftParkingStrategy(), inpArg[2]);
                                break;
                            case "NearExit":
                                entrance.setParkingPreferences(new NearExitParkingStrategy(), inpArg[2]);
                                break;
                            case "Default":
                                entrance.setParkingPreferences(new DefaultParkingStrategy(), inpArg[2]);
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
