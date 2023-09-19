package com.algaworks.ecommerce.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NOtaFiscalService;

public class GerarNotaFiscalListener {
	
	private NOtaFiscalService nOtaFiscalService = new NOtaFiscalService();

	@PrePersist
	@PreUpdate
	public void gerar(Pedido pedido) {
		if(pedido.isPago() && pedido.getNotaFiscal() == null) {
			nOtaFiscalService.gerar(pedido);
		}
	}

}
