package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {
	
	@Test
	public void impedirOperacaoComBancoDeDados() {
		Produto produto = entityManager.find(Produto.class, 1);
		// detach desanexa um instancia do entityManager
		entityManager.detach(produto);
		
		produto.setNome("Kindle Paperwhite 2 geracao");
		
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle", produtoVerificacao.getNome());
	}
	
	@Test
	public void mostrarDiferencaPersistMerge() {
		Produto produto = new Produto();
		
		produto.setNome("Smartfone one plus");
		produto.setDescricao("O procesador mas rapido");
		produto.setPreco(new BigDecimal(5000));
		
		// o persist so roda comandos de insert 
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		// limpa o entity manager fazendo com que a a proxima operação de find ja no banco de dados 
		// sem o "entityManager.clear()" ele pega o que esta na memoria
		entityManager.clear();
	
		Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacaoPersist);
		
		Produto produtoMerge = new Produto();
		
		produtoMerge.setNome("Notebook dell");
		produtoMerge.setDescricao("O melhor da categoria");
		produtoMerge.setPreco(new BigDecimal(2000));
		
		// o merge serge para atualizar e salvar 
		entityManager.getTransaction().begin();
		Produto produtoSalvo = entityManager.merge(produtoMerge);
		entityManager.getTransaction().commit();

		// limpa o entity manager fazendo com que a a proxima operação de find ja no banco de dados 
		// sem o "entityManager.clear()" ele pega o que esta na memoria
		entityManager.clear();
	
		Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoSalvo.getId());
		Assert.assertNotNull(produtoVerificacaoMerge);
	}
	
	@Test
	public void inseriObjetoComMerge() {
		Produto produto = new Produto();
		
		produto.setNome("Microfone rode videmic");
		produto.setDescricao("A melhor qualidade de som");
		produto.setPreco(new BigDecimal(5000));
		
		entityManager.getTransaction().begin();
		Produto produtoSalvo = entityManager.merge(produto);
		entityManager.getTransaction().commit();

		// limpa o entity manager fazendo com que a a proxima operação de find ja no banco de dados 
		// sem o "entityManager.clear()" ele pega o que esta na memoria
		entityManager.clear();
	
		Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void atualizarGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		produto.setNome("Kindle Paperwhite 2 geracao");
		
		
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals(produto.getNome(), produtoVerificacao.getNome());
	}
	
	@Test
	public void atualizarObjeto() {
		Produto produto = new Produto();
		
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conheca o novo kindle");
		produto.setPreco(new BigDecimal(599));
		
		
		entityManager.getTransaction().begin();
		Produto produtoSalvo = entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
		Assert.assertNotNull(produtoVerificacao);
		Assert.assertEquals(produto.getNome(), produtoVerificacao.getNome());
	}

	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
		// entityManager.clear(); não é necessario na asserção para operação de remocão
		
		Produto produtoVeriricacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNull(produtoVeriricacao);
	}
	
	@Test
	public void inserindoOPrimeiroObjeto() {
		Produto produto = new Produto();
		
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
