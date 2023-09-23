package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;

public class PassandoParametrosTest extends EntityManagerTest {

	@Test
	public void passarParamentro() {
		String jpql = "select p from Pedido p join p.pagamento pag "
				+ " where p.id = ?1 and pag.status = ?2 ";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		typedQuery.setParameter(1, 2);
		typedQuery.setParameter(2, StatusPagamento.PROCESSANDO);
		
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 1);
	}
	
}
