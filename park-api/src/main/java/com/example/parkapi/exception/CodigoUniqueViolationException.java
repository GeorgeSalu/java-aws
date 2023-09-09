package com.example.parkapi.exception;

public class CodigoUniqueViolationException extends RuntimeException {

	private static final long serialVersionUID = 4410385608788306900L;

	public CodigoUniqueViolationException(String message) {
		super(message);
	}
	
}
