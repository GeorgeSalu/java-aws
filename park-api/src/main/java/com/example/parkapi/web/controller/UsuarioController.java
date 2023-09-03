package com.example.parkapi.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkapi.entity.Usuario;
import com.example.parkapi.service.UsuarioService;
import com.example.parkapi.web.dto.UsuarioCreateDto;
import com.example.parkapi.web.dto.UsuarioResponseDto;
import com.example.parkapi.web.dto.UsuarioSenhaDto;
import com.example.parkapi.web.dto.mapper.UsuarioMapper;
import com.example.parkapi.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "Contem todas as operações relativas aos recursos para cadastro, edição e leitura de um usuario")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Operation(
			summary = "Criar um novo usuario",
			description = "Recurso para criar um novo usuario",
			responses = {
					@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "409", description = "Usuario e-mail ja cadastrado no sistema",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "recursos nao processados por dados de entrada invalidos",
						content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@PostMapping
	public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
		Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
	}
	
	@Operation(
			summary = "recuperar um usuario pelo id",
			description = "recuperar um usuario pelo id",
			responses = {
					@ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
					@ApiResponse(responseCode = "404", description = "recurso não encontrado",
						content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
		Usuario user = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(UsuarioMapper.toDto(user));
	}
	
	@Operation(
			summary = "atualizar senha",
			description = "atualizar senha",
			responses = {
					@ApiResponse(responseCode = "204", description = "senha atualizada com sucesso", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
					@ApiResponse(responseCode = "400", description = "senha não confere",
						content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "404", description = "recurso não encontrado",
						content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDto dto) {
		usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
		return ResponseEntity.noContent().build();
	}
	
	@Operation(
			summary = "Listar todos os usuarios",
			description = "listar todos os usuarios cadastrados",
			responses = {
					@ApiResponse(responseCode = "200", description = "lista com todos os usuarios cadastrados", 
							content = @Content(mediaType = "application/json", 
							array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))))
			}
	)
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDto>> getAll() {
		List<Usuario> users = usuarioService.buscarTodos();
		return ResponseEntity.ok(UsuarioMapper.toListDto(users));
	}

}
