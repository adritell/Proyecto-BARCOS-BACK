package com.api.sociosBarcos.exceptions;

public class SocioNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public SocioNotFoundException(String message) {
        super(message);
    }
}
