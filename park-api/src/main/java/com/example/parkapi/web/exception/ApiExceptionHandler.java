package com.example.parkapi.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.parkapi.exception.UsernameUniqueViolationException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(UsernameUniqueViolationException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValidException(RuntimeException ex, 
																		HttpServletRequest request) {
		log.error("Api Error - "+ex);
		return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, 
																		HttpServletRequest request,
																		BindingResult result) {
		log.error("Api Error - "+ex);
		return ResponseEntity
					.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalidos", result));
	}
}
