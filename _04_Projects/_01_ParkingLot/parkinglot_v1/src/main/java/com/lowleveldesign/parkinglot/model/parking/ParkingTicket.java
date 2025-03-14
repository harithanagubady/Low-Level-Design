package com.lowleveldesign.parkinglot.model.parking;

import com.lowleveldesign.parkinglot.model.enums.LocationType;
import com.lowleveldesign.parkinglot.model.enums.ParkingTicketStatus;
import com.lowleveldesign.parkinglot.model.vehicle.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ParkingTicket {

    private String ticketNo;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;
    ParkingTicketStatus parkingTicketStatus;
    private String entranceNo;
    private String exitNo;

    @Override
    public String toString() {
        String s1 = "Ticket: {"
                + "\n\t\tticketNo: " + ticketNo
                + ", \n\t\tvehicleNumber: " + vehicle.getLicensePlateNo()
                + ", \n\t\tvehicleType: " + vehicle.getVehicleType()
                + ", \n\t\tparkingType: " + parkingSpot.getParkingSpotType().name()
                + ", \n\t\tparkingSpotNo: " + parkingSpot.getSpotNumber()
                + ", \n\t\tentry: " + entryTime;
        if (parkingTicketStatus.equals(ParkingTicketStatus.PAID)) {
            s1 += (", \n\t\texit: " + exitTime + ", \n\t\tamount: " + amount);
        }
        s1 += (", \n\t\tticketStatus: " + parkingTicketStatus.name());
        s1 += "\n\t}";
        return s1;
    }
}
