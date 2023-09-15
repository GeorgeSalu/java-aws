package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

public class OperacoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void abrirEFecharUmaTransacao() {
		entityManager.getTransaction().begin();
		
		entityManager.getTransaction().commit();
	}
	
}
