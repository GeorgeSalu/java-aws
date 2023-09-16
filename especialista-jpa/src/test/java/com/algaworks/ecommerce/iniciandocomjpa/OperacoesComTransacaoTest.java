package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {
	
	
	@Test
	public void atualizarObjeto() {
		Produto produto = new Produto();
		
		produto.setId(1);
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conheca o novo kindle");
		produto.setPreco(new BigDecimal(599));
		
		
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
		Assert.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
	}

	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
		// entityManager.clear(); não é necessario na asserção para operação de remocão
		
		Produto produto2 = entityManager.find(Produto.class, 3);
		Assert.assertNull(produto2);
	}
	
	@Test
	public void inserindoOPrimeiroObjeto() {
		Produto produto = new Produto();
		
		produto.setId(2);
		produto.setNome("Camera canon");
		produto.setDescricao("A melhor definicao para suas fotos");
		produto.setPreco(new BigDecimal(5000));
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		// limpa o entity manager fazendo com que a a proxima operação de find ja no banco de dados 
		// sem o "entityManager.clear()" ele pega o que esta na memoria
		entityManager.clear();
	
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	
	@Test
	public void abrirEFecharUmaTransacao() {
		entityManager.getTransaction().begin();
		
		entityManager.getTransaction().commit();
	}
	
}
