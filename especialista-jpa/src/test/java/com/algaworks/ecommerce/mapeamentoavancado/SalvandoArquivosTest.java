package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class SalvandoArquivosTest extends EntityManagerTest {

	@Test
	public void salvarFotoProduto() {
		entityManager.getTransaction().begin();
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setFoto(carregarFoto());
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, 1);
		Assert.assertNotNull(produtoVerificacao.getFoto());
		Assert.assertTrue(produtoVerificacao.getFoto().length > 0);
	}

	@Test
	public void salvarXmlNota() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(carregarNotaFiscal());

		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();

		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assert.assertNotNull(notaFiscalVerificacao.getXml());
		Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);

	}

	private static byte[] carregarNotaFiscal() {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static byte[] carregarFoto() {
		return carregarArquivo("/kindle.jpg");
	}

	private static byte[] carregarArquivo(String nome) {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream(nome).readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
