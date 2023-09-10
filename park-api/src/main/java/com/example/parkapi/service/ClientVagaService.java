package com.example.parkapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.repository.ClienteVagaRepository;

@Service
public class ClientVagaService {

	private final ClienteVagaRepository repository;

	public ClientVagaService(ClienteVagaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ClienteVaga salvar(ClienteVaga clienteVaga) {
		return repository.save(clienteVaga);
	}

}
