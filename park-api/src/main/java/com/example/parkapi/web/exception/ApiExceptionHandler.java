package com.example.parkapi.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.parkapi.exception.CodigoUniqueViolationException;
import com.example.parkapi.exception.CpfUniqueViolationException;
import com.example.parkapi.exception.EntityNotFoundException;
import com.example.parkapi.exception.PasswordInvalidException;
import com.example.parkapi.exception.UsernameUniqueViolationException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
	// excecao resposavel para quanod vc nao tem permissao de acesso a um metodo
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException ex, 
																		HttpServletRequest request) {
		log.error("Api Error - "+ex);
		return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorMessage(request, HttpStatus.FORBIDDEN, ex.getMessage()));
	}
	
	@ExceptionHandler(PasswordInvalidException.class)
	public ResponseEntity<ErrorMessage> passwordInvalidException(RuntimeException ex, 
																		HttpServletRequest request) {
		log.error("Api Error - "+ex);
		return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex, 
																		HttpServletRequest request) {
		log.error("Api Error - "+ex);
		return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
	}
	
	@ExceptionHandler({UsernameUniqueViolationException.class, CpfUniqueViolationException.class, CodigoUniqueViolationException.class})
	public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex, 
																		HttpServletRequest request) {
		log.error("Api Error - "+ex);
		return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
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
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception ex, HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage(
                request, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("Internal Server Error {} {} ", error, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
