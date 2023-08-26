package com.example.parkapi.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto) {
		Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
		Usuario user = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(UsuarioMapper.toDto(user));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id,@RequestBody UsuarioSenhaDto dto) {
		Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> users = usuarioService.buscarTodos();
		return ResponseEntity.ok(users);
	}

}
