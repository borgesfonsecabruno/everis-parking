package br.com.everis.parking.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String identifier, String entity) {
        super("Não foi possível localizar o registro " + identifier + " de " + entity);
    }
}
