package br.com.everis.parking.dto.entry;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ParkingTicketCheckOutDTO {

    @NotNull
    private Long ticketId;

    private LocalDateTime departureDateTime;

    public ParkingTicketCheckOutDTO(Long ticketId, LocalDateTime departureDateTime) {
        this.ticketId = ticketId;
        this.departureDateTime = departureDateTime;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
}
