package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

public class FuncoesTest extends EntityManagerTest {

	@Test
	public void aplicarFuncaoNumero() {
		String jpql = "select abs(-10), mod(3,2), sqrt(9) from Pedido";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - "+ arr[1] + " - "+arr[2]));
	}
	
	@Test
	public void aplicarFuncoesData() {
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		//year(p.dataCriacao), month(p.dataCricao), day(p.dataCricao)
		
		String jpql = "select current_date, current_time, current_timestamp from Pedido p "
				+ " where p.dataCriacao < current_date";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - "+ arr[1] + " - "+arr[2]));
	}
	
	@Test
	public void aplicarFuncoesStrings() {
		// concat, length, locate, subsctring, lower, upper, trim
		String jpql = "select c.nome, length(c.nome) from Categoria c";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - "+ arr[1]));
	}
	
}
