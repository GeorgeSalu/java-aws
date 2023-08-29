package com.example.parkapi.exception;

public class UsernameUniqueViolationException extends RuntimeException {

	private static final long serialVersionUID = 569323222816955168L;

	public UsernameUniqueViolationException(String message) {
		super(message);
	}
	
}
