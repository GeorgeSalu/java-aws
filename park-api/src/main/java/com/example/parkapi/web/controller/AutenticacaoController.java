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
import com.example.parkapi.web.dto.UsuarioResponseDto;
import com.example.parkapi.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Tag(name = "Autenticação", description = "recurso para proceder com a autenticacao na api")
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
	
	@Operation(
			summary = "autenticar na api",
			description = "Recurso de autenticacao na api",
			responses = {
					@ApiResponse(responseCode = "200", description = "Autenticacao realizada com sucesso e retorno de um bearer token", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "400", description = "credenciais invalidas",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "campos invalidos",
						content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
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
