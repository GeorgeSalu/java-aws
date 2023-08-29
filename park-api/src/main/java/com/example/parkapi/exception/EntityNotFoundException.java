package com.example.parkapi.exception;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4067758293625920219L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
