package br.com.everis.parking.exceptions;

public class OperationNotAllowedException extends RuntimeException {

    public OperationNotAllowedException() {
        super();
    }

    public OperationNotAllowedException(String message) {
        super(message);
    }
}
