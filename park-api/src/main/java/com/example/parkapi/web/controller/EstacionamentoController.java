package com.example.parkapi.web.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.jwt.JwtUserDetails;
import com.example.parkapi.repository.projection.ClienteVagaProjection;
import com.example.parkapi.service.ClienteVagaService;
import com.example.parkapi.service.EstacionamentoService;
import com.example.parkapi.web.dto.EstacionamentoCreateDto;
import com.example.parkapi.web.dto.EstacionamentoResponseDto;
import com.example.parkapi.web.dto.PageableDto;
import com.example.parkapi.web.dto.mapper.ClienteVagaMapper;
import com.example.parkapi.web.dto.mapper.PageableMapper;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/estacionamentos")
public class EstacionamentoController {

	private final EstacionamentoService estacionamentoService;
	private final ClienteVagaService clienteVagaService;

	public EstacionamentoController(EstacionamentoService estacionamentoService,
			ClienteVagaService clienteVagaService) {
		this.estacionamentoService = estacionamentoService;
		this.clienteVagaService = clienteVagaService;
	}

	@PostMapping("/check-in")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EstacionamentoResponseDto> checkin(@RequestBody @Valid EstacionamentoCreateDto dto) {
		ClienteVaga clienteVaga = ClienteVagaMapper.toClienteVaga(dto);
		estacionamentoService.checkIn(clienteVaga);
		EstacionamentoResponseDto responseDto = ClienteVagaMapper.toDto(clienteVaga);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{recibo}")
				.buildAndExpand(clienteVaga.getRecibo())
				.toUri();
		return ResponseEntity.created(location).body(responseDto);
	}
	
    @GetMapping("/check-in/{recibo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    public ResponseEntity<EstacionamentoResponseDto> getByRecibo(@PathVariable String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }
    
    @PutMapping("/check-out/{recibo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDto> checkout(@PathVariable String recibo) {
        ClienteVaga clienteVaga = estacionamentoService.checkOut(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/cpf/{cpf}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> getAllEstacionamentosPorCpf(@PathVariable String cpf, @Parameter(hidden = true)
    @PageableDefault(size = 5, sort = "dataEntrada",
            direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorClienteCpf(cpf, pageable);
        PageableDto dto = PageableMapper.toDto(projection);
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PageableDto> getAllEstacionamentosDoCliente(@AuthenticationPrincipal JwtUserDetails user,
                                                                      @Parameter(hidden = true) @PageableDefault(
                                                                              size = 5, sort = "dataEntrada",
                                                                              direction = Sort.Direction.ASC) Pageable pageable) {

        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorUsuarioId(user.getId(), pageable);
        PageableDto dto = PageableMapper.toDto(projection);
        return ResponseEntity.ok(dto);
    }
}
