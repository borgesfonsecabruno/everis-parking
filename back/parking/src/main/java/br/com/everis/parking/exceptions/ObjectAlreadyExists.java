package br.com.everis.parking.exceptions;

import br.com.everis.parking.model.ParkingTicket;

public class ObjectAlreadyExists extends RuntimeException {
    public ObjectAlreadyExists() {
        super();
    }

    public ObjectAlreadyExists(ParkingTicket identifier) {
        super("Já existe um ticket em aberto de id " + identifier.getId() + " para o veículo");
    }

    public ObjectAlreadyExists(String identifier, String entity) {
        super("Já existe um registro " + identifier + " para " + entity);
    }
}
