package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

public class FuncoesTest extends EntityManagerTest {

	@Test
	public void paginarResultados() {
		// concat, length, locate, subsctring, lower, upper, trim
		String jpql = "select c.nome, length(c.nome) from Categoria c";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + " - "+ arr[1]));
	}
	
}
