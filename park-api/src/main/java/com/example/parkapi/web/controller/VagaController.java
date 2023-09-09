package com.example.parkapi.web.controller;

import java.net.URI;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/vagas")
public class VagaController {

	private final VagaService vagaService;

	public VagaController(VagaService vagaService) {
		this.vagaService = vagaService;
	}
	
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
