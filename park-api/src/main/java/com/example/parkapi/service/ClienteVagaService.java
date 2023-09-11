package com.example.parkapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.exception.EntityNotFoundException;
import com.example.parkapi.repository.ClienteVagaRepository;

@Service
public class ClienteVagaService {

	private final ClienteVagaRepository repository;

	public ClienteVagaService(ClienteVagaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ClienteVaga salvar(ClienteVaga clienteVaga) {
		return repository.save(clienteVaga);
	}

	@Transactional(readOnly = true)
	public ClienteVaga buscarPorRecibo(String recibo) {
		return repository.findByReciboAndDataSaidaIsNull(recibo).orElseThrow(
				() -> new EntityNotFoundException(
						String.format("Recibo '%s' não encontrado no sistema ou check-in ja realizado", recibo)
				)
		);
	}

}
