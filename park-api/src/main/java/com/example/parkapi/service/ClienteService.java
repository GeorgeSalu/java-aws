package com.example.parkapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.Cliente;
import com.example.parkapi.exception.CpfUniqueViolationException;
import com.example.parkapi.repository.ClienteRepository;

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
	
}
