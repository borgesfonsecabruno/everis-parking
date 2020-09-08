package br.com.everis.parking.dto.update;

import java.time.LocalDateTime;

public class ParkingTicketUpdateDTO {

    private LocalDateTime departureDateTime;

    public ParkingTicketUpdateDTO(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
}
