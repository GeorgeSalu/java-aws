package com.example.parkapi.service;

import java.math.BigDecimal;
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

	@Transactional
	public ClienteVaga checkOut(String recibo) {
		ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
		
		LocalDateTime dataSaida = LocalDateTime.now();
		
		BigDecimal valor = EstacionamentoUtils.calcularCusto(clienteVaga.getDataEntrada(), dataSaida);
		clienteVaga.setValor(valor);
		
		long totalDeVezes = clienteVagaService.getTotalDeVezesEstacionamentoCompleto(clienteVaga.getCliente().getCpf());
		
		BigDecimal desconto = EstacionamentoUtils.calcularDesconto(valor, totalDeVezes);
		clienteVaga.setDesconto(desconto);
		
		clienteVaga.setDataSaida(dataSaida);
		clienteVaga.getVaga().setStatus(StatusVaga.LIVRE);
		
		return clienteVagaService.salvar(clienteVaga);
	}

}
