package com.example.parkapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.exception.EntityNotFoundException;
import com.example.parkapi.repository.ClienteVagaRepository;
import com.example.parkapi.repository.projection.ClienteVagaProjection;

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

	@Transactional(readOnly = true)
	public long getTotalDeVezesEstacionamentoCompleto(String cpf) {
		return repository.countByClienteCpfAndDataSaidaIsNotNull(cpf);
	}

	@Transactional(readOnly = true)
	public Page<ClienteVagaProjection> buscarTodosPorClienteCpf(String cpf, Pageable pageable) {
		return repository.findAllByClienteCpf(cpf, pageable);
	}

	@Transactional(readOnly = true)
	public Page<ClienteVagaProjection> buscarTodosPorUsuarioId(Long id, Pageable pageable) {
		return repository.findAllByClienteUsuarioId(id, pageable);
	}

}
