package br.com.everis.parking.dto.response;

import br.com.everis.parking.model.ParkingTicket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingTicketResponseDTO {
    private Long id;

    private LocalDateTime entryDateTime;

    private LocalDateTime departureDateTime;

    private BigDecimal totalParking;

    private VehicleResponseDTO vehicle;

    private PriceFactorResponseDTO priceFactor;

    private ParkingResponseDTO parking;

    public ParkingTicketResponseDTO(ParkingTicket parkingTicket) {
        this.id = parkingTicket.getId();
        this.entryDateTime = parkingTicket.getEntryDateTime();
        this.departureDateTime = parkingTicket.getDepartureDateTime();
        this.totalParking = parkingTicket.getTotalParking();
        this.vehicle = new VehicleResponseDTO(parkingTicket.getVehicle());
        this.priceFactor = new PriceFactorResponseDTO(parkingTicket.getPriceFactor());
        this.parking = new ParkingResponseDTO(parkingTicket.getParking());
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public BigDecimal getTotalParking() {
        return totalParking;
    }

    public VehicleResponseDTO getVehicle() {
        return vehicle;
    }

    public PriceFactorResponseDTO getPriceFactor() {
        return priceFactor;
    }

    public ParkingResponseDTO getParking() {
        return parking;
    }
}
