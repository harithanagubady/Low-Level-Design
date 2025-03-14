package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.exception.ErrorConstants;
import com.lowleveldesign.parkinglot.exception.ParkingLotFullException;
import com.lowleveldesign.parkinglot.exception.ParkingLotUnavailableException;
import com.lowleveldesign.parkinglot.exception.ParkingSpotUnavailableException;
import com.lowleveldesign.parkinglot.model.enums.ParkingTicketStatus;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Entrance {

    private final String entranceNumber;
    private ParkingStrategy parkingStrategy;

    public Entrance (String entranceNumber) {
        this.entranceNumber = entranceNumber;
        this.parkingStrategy = new DefaultParkingStrategy();
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingTicket issueTicket (Vehicle vehicle) {
        if (ParkingLot.getInstance().isLotFull()) {
            throw new ParkingLotFullException(ErrorConstants.PARKING_LOT_FULL_MSG);
        }
        ParkingSpot parkingSpot = ParkingLot.getInstance().assignParkingSpot(vehicle, parkingStrategy);
        if (parkingSpot == null) {
            throw new ParkingSpotUnavailableException("Parking Spot for " + vehicle.getVehicleType() + " is unavailable");
        }
        ParkingTicket ticket = buildParkingTicket(parkingSpot, vehicle);
        System.out.println(ticket);
        return ticket;
    }

    private ParkingTicket buildParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
        return ParkingTicket.builder()
                .parkingTicketStatus(ParkingTicketStatus.ACTIVE)
                .parkingSpot(parkingSpot)
                .vehicle(vehicle)
                .entryTime(LocalDateTime.now())
                .ticketNo(UUID.randomUUID().toString())
                .build();
    }

    @Override
    public String toString() {
        return "\t\tEntrance: {" +
                "entranceNumber='" + entranceNumber + '\'' +
                '}';
    }
}
