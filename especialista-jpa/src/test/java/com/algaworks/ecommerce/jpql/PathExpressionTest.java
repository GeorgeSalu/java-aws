package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void buscarPedidosComProdutoEspecifico() {
        String jpql = "select p from Pedido p join p.itens i where i.id.produtoId = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 2);
    }
	
	@Test
	public void usarPathExpressions() {
		
		String jpql = "select p.cliente.nome from Pedido p";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
	}
	
}
