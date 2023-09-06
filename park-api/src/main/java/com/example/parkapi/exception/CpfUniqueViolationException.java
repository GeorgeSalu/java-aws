package com.example.parkapi.exception;

public class CpfUniqueViolationException extends RuntimeException {
	
	private static final long serialVersionUID = 2361757478958520532L;

	public CpfUniqueViolationException(String message) {
		super(message);
	}
	
}
