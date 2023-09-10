package com.example.parkapi.service;

import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {

	private final ClienteVagaService clienteVagaService;
	private final ClienteService clienteService;
	private final VagaService service;

	public EstacionamentoService(ClienteVagaService clienteVagaService, ClienteService clienteService,
			VagaService service) {
		this.clienteVagaService = clienteVagaService;
		this.clienteService = clienteService;
		this.service = service;
	}
	

}
