package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.ParkingLotFullException;
import com.lowleveldesign.parkinglot.exception.ParkingSpotUnavailableException;
import com.lowleveldesign.parkinglot.model.enums.ParkingTicketStatus;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Entrance {

    private String entranceNo;
    private ParkingPreferences parkingPreferences; //default

    public Entrance(String entranceNo) {
        this.entranceNo = entranceNo;
        this.parkingPreferences = new ParkingPreferences(new DefaultParkingStrategy(), "-1");
    }

    public void setParkingPreferences(ParkingStrategy parkingStrategy, String num) {
        if (parkingStrategy != null && num != null) {
            this.parkingPreferences = new ParkingPreferences(parkingStrategy, num);
        } else {
            throw new IllegalArgumentException("Input error: " + parkingStrategy + ", " + num);
        }
    }

    public ParkingTicket issueTicket(Vehicle vehicle) {
        if (ParkingLot.getInstance().isLotFull()) {
            throw new ParkingLotFullException(ErrorConstants.PARKING_LOT_FULL_MSG);
        }
        ParkingSpot parkingSpot = ParkingLot.getInstance().assignParkingSpot(vehicle, this.parkingPreferences);

        if (parkingSpot == null) {
            throw new ParkingSpotUnavailableException("Parking Spot for " + vehicle.getVehicleType() + " is unavailable");
        }
        ParkingTicket ticket = buildParkingTicket(parkingSpot, vehicle);
        System.out.println("Ticket issued at entrance gate: " + entranceNo + "\n" + ticket);
        return ticket;
    }

    private ParkingTicket buildParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
        return ParkingTicket.builder()
                .parkingTicketStatus(ParkingTicketStatus.ACTIVE)
                .parkingSpot(parkingSpot)
                .vehicle(vehicle)
                .entryTime(LocalDateTime.now())
                .ticketNo(UUID.randomUUID().toString())
                .entranceNo(entranceNo)
                .build();
    }
}
