package com.example.parkapi.web.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.parkapi.entity.Vaga;
import com.example.parkapi.service.VagaService;
import com.example.parkapi.web.dto.VagaCreateDto;
import com.example.parkapi.web.dto.VagaResponseDto;
import com.example.parkapi.web.dto.mapper.VagaMapper;
import com.example.parkapi.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/vagas")
public class VagaController {

	private final VagaService vagaService;

	public VagaController(VagaService vagaService) {
		this.vagaService = vagaService;
	}
	
    @Operation(summary = "Criar uma nova vaga", description = "Recurso para criar uma nova vaga." +
            "Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            headers = @Header(name = HttpHeaders.LOCATION, description = "URL do recurso criado")),
                    @ApiResponse(responseCode = "409", description = "Vaga já cadastrada",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por falta de dados ou dados inválidos",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permito ao perfil de CLIENTE",
                            content = @Content(mediaType = " application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> create(@RequestBody @Valid VagaCreateDto dto) {
		Vaga vaga = VagaMapper.toVaga(dto);
		vagaService.salvar(vaga);
		URI location = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{codigo}")
					.buildAndExpand(vaga.getCodigo())
					.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VagaResponseDto> create(@PathVariable String codigo) {
		Vaga vaga = vagaService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(VagaMapper.toDto(vaga));
	}
	
}
