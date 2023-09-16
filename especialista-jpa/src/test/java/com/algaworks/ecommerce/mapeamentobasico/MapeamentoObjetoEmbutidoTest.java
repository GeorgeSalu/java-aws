package com.algaworks.ecommerce.mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

	@Test
	public void analisarMapeamentoObjetoEmbutido() {
		EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
		endereco.setCep("000-112");
		endereco.setLogradouro("Rua das laranjeiras");
		endereco.setNumero("1212");
		endereco.setBairro("novo");
		endereco.setCidade("nova");
		
		
		Pedido pedido = new Pedido();
		pedido.setId(1);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(new BigDecimal(10000));
		pedido.setEnderecoEntrega(endereco);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());
	}
	
}
