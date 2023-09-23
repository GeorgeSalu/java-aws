package com.algaworks.ecommerce.jpql;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest {

	@Test
	public void buscarPorIdentificador() {
		// entityManager.find(Pedido.class, 1);

		TypedQuery<Pedido> typedQuery = entityManager
				.createQuery(" select p from Pedido p where p.id = 1 ", Pedido.class);
		
		Pedido pedido = typedQuery.getSingleResult();
		Assert.assertNotNull(pedido);
		
//		List<Pedido> lista = typedQuery.getResultList();
//		Assert.assertFalse(lista.isEmpty());
	}
	
}
