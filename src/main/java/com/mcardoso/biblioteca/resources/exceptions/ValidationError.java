package com.mcardoso.biblioteca.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> errorList = new ArrayList<>();

    public ValidationError() {
    }
    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public List<FieldMessage> getErrorList() {
        return errorList;
    }
    public void addErrorList(String fieldName, String message) {
        this.errorList.add(new FieldMessage(fieldName, message));
    }
}
