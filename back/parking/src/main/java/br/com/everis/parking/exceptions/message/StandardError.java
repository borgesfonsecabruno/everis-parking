package br.com.everis.parking.exceptions.message;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;
    private LocalDateTime datetime;

    public StandardError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.datetime = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

}
