package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class PaginacaoJPQLTest extends EntityManagerTest {

	@Test
	public void paginarResultados() {
		String jpql = "select c from Categoria c order by c.nome";
		
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
		
		// first_result = max_results * (pagina - 1)
		//typedQuery.setFirstResult(2);
		// limita a quantidade de resgistros retornados
		typedQuery.setMaxResults(2);		
		
		List<Categoria> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(p -> System.out.println(p.getId() + ", "+p.getNome()));
	}
	
}
