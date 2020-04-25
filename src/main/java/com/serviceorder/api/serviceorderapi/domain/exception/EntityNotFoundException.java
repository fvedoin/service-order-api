package com.serviceorder.api.serviceorderapi.domain.exception;

public class EntityNotFoundException extends RuleException{

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
