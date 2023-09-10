package com.example.parkapi.service;

import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {

	private final ClientVagaService clientVagaService;
	private final ClienteService clienteService;
	private final VagaService service;

	public EstacionamentoService(ClientVagaService clientVagaService, ClienteService clienteService,
			VagaService service) {
		this.clientVagaService = clientVagaService;
		this.clienteService = clienteService;
		this.service = service;
	}

}
