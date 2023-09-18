package com.algaworks.ecommerce.relacionamentos;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class EagerELazyTest extends EntityManagerTest {

	@Test
	public void verificarComportamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		/*
		 * comportamento padrao - quando um objeto ele ja faz a consulta mas quando uma lista ele so faz a conssulta quando essa lista é usada
		 * 
		 * */
		pedido.getItens().isEmpty();
	}
	
}
