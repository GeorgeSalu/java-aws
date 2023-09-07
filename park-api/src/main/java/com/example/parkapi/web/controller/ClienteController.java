package com.example.parkapi.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkapi.entity.Cliente;
import com.example.parkapi.jwt.JwtUserDetails;
import com.example.parkapi.repository.projection.ClienteProjection;
import com.example.parkapi.service.ClienteService;
import com.example.parkapi.service.UsuarioService;
import com.example.parkapi.web.dto.ClienteCreateDto;
import com.example.parkapi.web.dto.ClienteResponseDto;
import com.example.parkapi.web.dto.PageableDto;
import com.example.parkapi.web.dto.mapper.ClienteMapper;
import com.example.parkapi.web.dto.mapper.PageableMapper;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.web.PageableDefault;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	private final UsuarioService usuarioService;

	public ClienteController(ClienteService clienteService, UsuarioService usuarioService) {
		this.clienteService = clienteService;
		this.usuarioService = usuarioService;
	}


	@PostMapping
	@PreAuthorize("hasRole('CLIENTE')")
	public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto,
													@AuthenticationPrincipal JwtUserDetails userDetails) {
		Cliente cliente = ClienteMapper.toCliente(dto);
		cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
		clienteService.salvar(cliente);
		return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDto> getById(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok(ClienteMapper.toDto(cliente));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageableDto> getAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
		Page<ClienteProjection> clientes = clienteService.buscarTodos(pageable);
		return ResponseEntity.ok(PageableMapper.toDto(clientes));
	}
	
	@GetMapping("/detalhes")
	@PreAuthorize("hasRole('CLIENTE')")
	public ResponseEntity<ClienteResponseDto> getAll(@AuthenticationPrincipal JwtUserDetails userDetails) {
		Cliente cliente = clienteService.buscarPorUsuarioId(userDetails.getId());
		return ResponseEntity.ok(ClienteMapper.toDto(cliente));
	}
	
}
