package br.com.everis.parking.exceptions.message;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> fieldMessageList;

    public ValidationError(HttpStatus status, String message) {
        super(status, message);
        this.fieldMessageList = new ArrayList<>();
    }

    public List<FieldMessage> getFieldExceptionList() {
        return Collections.unmodifiableList(fieldMessageList);
    }

    public void setFieldExceptionList(FieldMessage field) {
        this.fieldMessageList.add(field);
    }
}
