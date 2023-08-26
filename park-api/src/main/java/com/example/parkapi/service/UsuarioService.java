package com.example.parkapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.Usuario;
import com.example.parkapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado")
		);
	}

	@Transactional
	public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String comfirmarSenha) {
		if(!novaSenha.equals(comfirmarSenha)) {
			throw new RuntimeException("Nova senha não confere com confirmação de senha");
		}
		Usuario user = buscarPorId(id);
		if(!user.getPassword().equals(senhaAtual)) {
			throw new RuntimeException("Sua senha nao confere");
		}
			
		user.setPassword(novaSenha);
		return user;
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
	
	
}