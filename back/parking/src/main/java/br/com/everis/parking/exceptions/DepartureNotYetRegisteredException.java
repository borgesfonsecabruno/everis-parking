package br.com.everis.parking.exceptions;

import br.com.everis.parking.model.ParkingTicket;

public class DepartureNotYetRegisteredException extends RuntimeException {

    public DepartureNotYetRegisteredException() {
        super();
    }

    public DepartureNotYetRegisteredException(ParkingTicket identifier) {
        super("Registre a saída do ticket " + identifier.getId());
    }
}
