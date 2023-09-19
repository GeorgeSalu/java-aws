package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ContextoDePersistenciaTest extends EntityManagerTest {

	@Test
	public void usarContextoPersitencia() {
		
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(new BigDecimal(100.0));
		
		
		entityManager.getTransaction().commit();
		
	}
	
}
