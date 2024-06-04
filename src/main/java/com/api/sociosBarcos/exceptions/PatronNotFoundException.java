package com.api.sociosBarcos.exceptions;

public class PatronNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public PatronNotFoundException(String message) {
        super(message);
    }
}