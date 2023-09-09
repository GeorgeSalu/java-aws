package com.example.parkapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.Vaga;
import com.example.parkapi.exception.CodigoUniqueViolationException;
import com.example.parkapi.exception.EntityNotFoundException;
import com.example.parkapi.repository.VagaRepository;

@Service
public class VagaService {

	private final VagaRepository vagaRepository;

	public VagaService(VagaRepository vagaRepository) {
		this.vagaRepository = vagaRepository;
	}
	
	@Transactional
	public Vaga salvar(Vaga vaga) {
		try {
			return vagaRepository.save(vaga);
		} catch (DataIntegrityViolationException e) {
			throw new CodigoUniqueViolationException(String.format("Vaga com codigo '%s' ja cadastrado", vaga.getCodigo()));
		}
	}
	
	@Transactional(readOnly = true)
	public Vaga buscarPorCodigo(String codigo) {
		return vagaRepository.findByCodigo(codigo).orElseThrow(
				() -> new EntityNotFoundException(String.format("Vaga com codigo '%s' não encontrado", codigo))
		);
	}
}
