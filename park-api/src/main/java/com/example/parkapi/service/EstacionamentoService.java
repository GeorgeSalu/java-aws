package com.example.parkapi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.parkapi.entity.Cliente;
import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.entity.Vaga;
import com.example.parkapi.entity.Vaga.StatusVaga;
import com.example.parkapi.util.EstacionamentoUtils;

@Service
public class EstacionamentoService {

	private final ClienteVagaService clienteVagaService;
	private final ClienteService clienteService;
	private final VagaService vagaService;

	public EstacionamentoService(ClienteVagaService clienteVagaService, ClienteService clienteService,
			VagaService vagaService) {
		this.clienteVagaService = clienteVagaService;
		this.clienteService = clienteService;
		this.vagaService = vagaService;
	}

	@Transactional
	public ClienteVaga checkIn(ClienteVaga clienteVaga) {
		Cliente cliente = clienteService.buscarPorCpf(clienteVaga.getCliente().getCpf());
		clienteVaga.setCliente(cliente);

		Vaga vaga = vagaService.buscarPorVagaLivre();
		vaga.setStatus(StatusVaga.OCUPADA);
		clienteVaga.setVaga(vaga);

		clienteVaga.setDataEntrada(LocalDateTime.now());

		clienteVaga.setRecibo(EstacionamentoUtils.gerarRecibo());

		return clienteVagaService.salvar(clienteVaga);
	}

}
