package com.algaworks.ecommerce.jpql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class ExpressoesCondicionaisTest extends EntityManagerTest {
	
	@Test
	public void usarExpressaoCase() {
		String jpql = "select p.id, "
				+ " case p.status "
				+ "      when 'PAGO' then 'Esta pago' "
				+ "      when 'CANCELADO' then 'Foi cancelado' "
				+ "      else 'Esta aguadando' "
				+ " end "
				+ " from Pedido p ";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " , "+arr[1]));
	}
	
	@Test
	public void usarExpressaoDiferente() {
		String jpql = "select p from Produto p where p.id <> 1";
		
		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
		
		List<Produto> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarBetween() {
		String jpql = "select p from Produto p "
				+ " where p.preco between :precoInicial and :precoFinal";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("precoInicial", new BigDecimal(400));
		typedQuery.setParameter("precoFinal", new BigDecimal(1500));
		
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
    @Test
    public void usarMaiorMenorComDatas() {
        String jpql = "select p from Pedido p where p.dataCriacao > :data";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("data", LocalDateTime.now().minusDays(2));

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
	
	@Test
	public void usarMaiorMenor() {
		String jpql = "select p from Produto p "
				+ " where p.preco >= :precoInicial and p.preco <= :precoFinal";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("precoInicial", new BigDecimal(400));
		typedQuery.setParameter("precoFinal", new BigDecimal(1500));
		
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarIsNull() {
		String jpql = "select p from Produto p where p.foto is null";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarIsEmpty() {
		String jpql = "select p from Produto p where p.categorias is empty";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void usarExpressaoCondicionalLike() {
		String jpql = "select c from Cliente c where c.nome like concat(:nome, '%') ";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("nome", "Fernando");
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
}
