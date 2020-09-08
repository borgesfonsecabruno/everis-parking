package br.com.everis.parking.exceptions.message;

import org.springframework.http.HttpStatus;

public class TypeMismatchError extends StandardError {

    private FieldMessage fieldError;

    public TypeMismatchError(HttpStatus status, String message, FieldMessage fieldError) {
        super(status, message);
        this.fieldError = fieldError;
    }

    public FieldMessage getFieldError() {
        return fieldError;
    }
}
