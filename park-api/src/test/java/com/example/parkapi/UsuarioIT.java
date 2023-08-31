package com.example.parkapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.parkapi.web.dto.UsuarioCreateDto;
import com.example.parkapi.web.dto.UsuarioResponseDto;
import com.example.parkapi.web.exception.ErrorMessage;

@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioIT {

	@Autowired
	WebTestClient testClient;
	
	@Test
	public void createUsuario_ComUsernameEPasswordValidos_RetornarUsuarioCriadoComStatus201() {
		UsuarioResponseDto responseBody = testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("tody@gmail.com","123456"))
			.exchange()
			.expectStatus().isCreated()
			.expectBody(UsuarioResponseDto.class)
			.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getId()).isNotNull();
		Assertions.assertThat(responseBody.getUsername()).isNotNull();
		Assertions.assertThat(responseBody.getUsername()).isEqualTo("tody@gmail.com");
		Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
	}
	
	@Test
	public void createUsuario_ComUsernameInvalidos_RetornarErrorMessageStatus422() {
		ErrorMessage responseBody = testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("","123456"))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = testClient
				.post()
				.uri("/api/v1/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioCreateDto("tody@","123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
			Assertions.assertThat(responseBody).isNotNull();
			Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
			
			responseBody = testClient
					.post()
					.uri("/api/v1/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(new UsuarioCreateDto("tody@email","123456"))
					.exchange()
					.expectStatus().isEqualTo(422)
					.expectBody(ErrorMessage.class)
					.returnResult().getResponseBody();
				
			Assertions.assertThat(responseBody).isNotNull();
			Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	}
	
	@Test
	public void createUsuario_ComPasswordInvalidos_RetornarErrorMessageStatus422() {
		ErrorMessage responseBody = testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("tody@gmail.com",""))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = testClient
				.post()
				.uri("/api/v1/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UsuarioCreateDto("tody@gmail.com","123456233"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			
			Assertions.assertThat(responseBody).isNotNull();
			Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
			
			responseBody = testClient
					.post()
					.uri("/api/v1/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(new UsuarioCreateDto("tody@gmail.com","156"))
					.exchange()
					.expectStatus().isEqualTo(422)
					.expectBody(ErrorMessage.class)
					.returnResult().getResponseBody();
				
			Assertions.assertThat(responseBody).isNotNull();
			Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	}
	
	@Test
	public void createUsuario_ComUsernameRepetido_RetornarErroMessageComStatus409() {
		ErrorMessage responseBody = testClient
			.post()
			.uri("/api/v1/usuarios")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UsuarioCreateDto("ana@gmail.com","123456"))
			.exchange()
			.expectStatus().isEqualTo(409)
			.expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
	}
	
	@Test
	public void buscarUsuario_ComIdExistente_RetornarUsuarioComStatus200() {
		UsuarioResponseDto responseBody = testClient
			.get()
			.uri("/api/v1/usuarios/100")
			.exchange()
			.expectStatus().isOk()
			.expectBody(UsuarioResponseDto.class)
			.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getId()).isNotNull();
		Assertions.assertThat(responseBody.getUsername()).isNotNull();
		Assertions.assertThat(responseBody.getUsername()).isEqualTo("ana@gmail.com");
		Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void buscarUsuario_ComIdInexistente_RetornarErroMessageComStatus404() {
		ErrorMessage responseBody = testClient
			.get()
			.uri("/api/v1/usuarios/0")
			.exchange()
			.expectStatus().isNotFound()
			.expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	}
	
	
}
