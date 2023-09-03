package com.example.parkapi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkapi.jwt.JwtToken;
import com.example.parkapi.jwt.JwtUserDetailsService;
import com.example.parkapi.web.dto.UsuarioLoginDto;
import com.example.parkapi.web.exception.ErrorMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {
	
	private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);

	private final JwtUserDetailsService detailsService;
	private final AuthenticationManager authenticationManager;
	
	public AutenticacaoController(JwtUserDetailsService detailsService, AuthenticationManager authenticationManager) {
		this.detailsService = detailsService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request) {
		log.info("Processo de autenticacao pelo login {}", dto.getUsername());
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
			authenticationManager.authenticate(authenticationToken);
			JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());
			return ResponseEntity.ok(token);
		} catch (AuthenticationException e) {
			log.warn("Bad credentials from username {} ", dto.getUsername());
		}
		return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "credenciais invalidas"));
	}
	
}
