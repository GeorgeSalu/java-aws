package com.algaworks.ecommerce.criteira;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class BasicoCriteiraTest extends EntityManagerTest {

	@Test
	public void buscarPorIdentificador() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		
		criteriaQuery.select(root);
		
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Pedido pedido = typedQuery.getSingleResult();
		Assert.assertNotNull(pedido);
		
	}
	
}
