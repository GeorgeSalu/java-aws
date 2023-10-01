package com.algaworks.ecommerce.criteira;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.Pedido;

public class JoinCriteriaTest extends EntityManagerTest {

	@Test
	public void fazerJoin() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pagamento> criteriaQuery = criteriaBuilder.createQuery(Pagamento.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
		
		criteriaQuery.select(joinPagamento);
		
		TypedQuery<Pagamento> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Pagamento> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
	}
	
}
