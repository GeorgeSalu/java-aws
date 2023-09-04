package com.example.parkapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.parkapi.jwt.JwtToken;
import com.example.parkapi.web.dto.UsuarioLoginDto;
import com.example.parkapi.web.exception.ErrorMessage;

@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutenticacaoIT {

	@Autowired
	WebTestClient testClient;
	
   @Test
    public void autenticar_ComCredenciaisValidas_RetornarTokenComStatus200() {
        JwtToken responseBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioLoginDto("ana@email.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
    }

   @Test
   public void autenticar_ComCredenciaisInvalidas_RetornarErrorMessageStatus400() {
       ErrorMessage responseBody = testClient
               .post()
               .uri("/api/v1/auth")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioLoginDto("invalido@email.com", "123456"))
               .exchange()
               .expectStatus().isBadRequest()
               .expectBody(ErrorMessage.class)
               .returnResult().getResponseBody();

       Assertions.assertThat(responseBody).isNotNull();
       Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);

       responseBody = testClient
               .post()
               .uri("/api/v1/auth")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioLoginDto("ana@email.com", "000000"))
               .exchange()
               .expectStatus().isBadRequest()
               .expectBody(ErrorMessage.class)
               .returnResult().getResponseBody();

       Assertions.assertThat(responseBody).isNotNull();
       Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
   }
	
   @Test
   public void autenticar_ComUsernameInvalido_RetornarErrorMessageStatus422() {
       ErrorMessage responseBody = testClient
               .post()
               .uri("/api/v1/auth")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioLoginDto("", "123456"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult().getResponseBody();

       Assertions.assertThat(responseBody).isNotNull();
       Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

       responseBody = testClient
               .post()
               .uri("/api/v1/auth")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(new UsuarioLoginDto("@email.com", "123456"))
               .exchange()
               .expectStatus().isEqualTo(422)
               .expectBody(ErrorMessage.class)
               .returnResult().getResponseBody();

       Assertions.assertThat(responseBody).isNotNull();
       Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
   }

}
