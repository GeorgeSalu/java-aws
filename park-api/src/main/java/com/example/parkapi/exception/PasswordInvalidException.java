package com.example.parkapi.exception;

public class PasswordInvalidException extends RuntimeException {

	private static final long serialVersionUID = 8211222694967581439L;

	public PasswordInvalidException(String message) {
		super(message);
	}
	
}
