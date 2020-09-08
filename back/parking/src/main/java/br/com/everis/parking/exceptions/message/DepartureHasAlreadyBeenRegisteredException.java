package br.com.everis.parking.exceptions.message;

import br.com.everis.parking.model.ParkingTicket;

public class DepartureHasAlreadyBeenRegisteredException extends RuntimeException {

    public DepartureHasAlreadyBeenRegisteredException() {
        super();
    }

    public DepartureHasAlreadyBeenRegisteredException(ParkingTicket identifier) {
        super("Ticket " + identifier.getId() + " já fez check out");
    }
}
