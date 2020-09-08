package br.com.everis.parking.exceptions;

public class FactorNotAllowedException extends RuntimeException {

    public FactorNotAllowedException() {
        super("Desconto não pode ser maior que 100%");
    }
}
