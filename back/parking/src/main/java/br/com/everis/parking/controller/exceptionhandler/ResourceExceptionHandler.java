package br.com.everis.parking.controller.exceptionhandler;

import br.com.everis.parking.exceptions.DepartureNotYetRegisteredException;
import br.com.everis.parking.exceptions.ObjectAlreadyExists;
import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.exceptions.message.*;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice // Tratamento de erros e auxilio do controller
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StandardError messageError = new StandardError(HttpStatus.BAD_REQUEST, "Internal Error");
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST, "Validation error");

        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationError.setFieldExceptionList(
                    new FieldMessage(error.getField(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FieldMessage fieldError = new FieldMessage(ex.getValue().toString(), "Tipo exigido: " + ex.getRequiredType().getSimpleName());
        TypeMismatchError messageError = new TypeMismatchError(HttpStatus.BAD_REQUEST, "Erro de tipo", fieldError);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StandardError messageError = new StandardError(HttpStatus.BAD_REQUEST, "Não foi possível ler o corpo da requisição");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StandardError messageError = new StandardError(
                HttpStatus.BAD_REQUEST,
                ex.getMethod() +
                        " não suportado. Nesse contexto suporta-se os métodos: " +
                        Arrays.toString(ex.getSupportedMethods()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }

    @ExceptionHandler({
            ObjectNotFoundException.class,
            ObjectAlreadyExists.class,
            DepartureNotYetRegisteredException.class,
            DepartureHasAlreadyBeenRegisteredException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<Object> handleObjectNotFoundException(Exception ex) {
        StandardError messageError = new StandardError(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }
}
