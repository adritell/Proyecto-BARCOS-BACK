package com.api.sociosBarcos.exceptions;

public class BarcoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public BarcoNotFoundException(String message) {
        super(message);
    }
}