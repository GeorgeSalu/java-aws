package com.example.parkapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.Cliente;
import com.example.parkapi.exception.CpfUniqueViolationException;
import com.example.parkapi.exception.EntityNotFoundException;
import com.example.parkapi.repository.ClienteRepository;
import com.example.parkapi.repository.projection.ClienteProjection;


@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		try {
			return clienteRepository.save(cliente);
		} catch (DataIntegrityViolationException e) {
			throw new CpfUniqueViolationException(
					String.format("cpf '%s' não pode ser cadastrado, ja existe no sistema", cliente.getCpf())
			);
		}
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema", id))
		);
	}

	@Transactional(readOnly = true)
	public Page<ClienteProjection> buscarTodos(Pageable pageable) {
		return clienteRepository.findAllPageable(pageable);
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorUsuarioId(Long id) {
		return clienteRepository.findByUsuarioId(id);
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorCpf(String cpf) {
		return clienteRepository.findByCpf(cpf).orElseThrow(
				() -> new EntityNotFoundException(String.format("Cliente com cpf '%s' não encontrado", cpf))
		);
	}
	
}
