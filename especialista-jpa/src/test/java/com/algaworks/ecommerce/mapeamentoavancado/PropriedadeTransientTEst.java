package com.algaworks.ecommerce.mapeamentoavancado;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

import junit.framework.Assert;

public class PropriedadeTransientTEst extends EntityManagerTest {

	@Test
	public void validarPrimeiroNome() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertEquals("Fernando", cliente.getPrimeiroNome());
	}
	
}
