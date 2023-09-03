package com.example.parkapi.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.Usuario;
import com.example.parkapi.exception.EntityNotFoundException;
import com.example.parkapi.exception.PasswordInvalidException;
import com.example.parkapi.exception.UsernameUniqueViolationException;
import com.example.parkapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		try {			
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new UsernameUniqueViolationException(String.format("Username {%s} ja cadastrado", usuario.getUsername()));
		}
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuario id=%s não encontrado", id))
		);
	}

	@Transactional
	public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String comfirmarSenha) {
		if(!novaSenha.equals(comfirmarSenha)) {
			throw new PasswordInvalidException("Nova senha não confere com confirmação de senha");
		}
		
		Usuario user = buscarPorId(id);
		if(!passwordEncoder.matches(senhaAtual, user.getPassword())) {
			throw new PasswordInvalidException("Sua senha nao confere");
		}
			
		user.setPassword(passwordEncoder.encode(novaSenha));
		return user;
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorUsername(String username) {
		return usuarioRepository.findByUsername(username).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", username))
		);
	}

	@Transactional(readOnly = true)
	public Usuario.Role buscarRolePorUsername(String username) {
		return usuarioRepository.findByRoleByUsername(username);
	}
	
	
}
