package com.algaworks.ecommerce.consultasNativas;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ConsultaNativaTest extends EntityManagerTest {

	@Test
	public void executarSQLRetornoEntidade() {
		String sql = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto from produto";
		
		Query query = entityManager.createNativeQuery(sql, Produto.class);
		
		List<Produto> lista = query.getResultList();
		
		lista.stream().forEach(obj -> System.out.println(
				String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
	}
	
	@Test
	public void executarSQL() {
		String sql = "select id, nome from produto";
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();
		
		lista.stream().forEach(arr -> System.out.println(
				String.format("Produto => ID: %s, Nome: %s", arr[0], arr[1])));
	}
	
}
