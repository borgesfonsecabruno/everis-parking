package br.com.everis.parking.dto.entry;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ParkingTicketCheckOutDTO {

    @NotNull
    private Long ticket_id;

    private LocalDateTime departureDateTime;

    public ParkingTicketCheckOutDTO(Long ticket_id, LocalDateTime departureDateTime) {
        this.ticket_id = ticket_id;
        this.departureDateTime = departureDateTime;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
}
