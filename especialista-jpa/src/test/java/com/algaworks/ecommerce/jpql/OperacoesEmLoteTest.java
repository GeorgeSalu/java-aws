package com.algaworks.ecommerce.jpql;

import javax.persistence.Query;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

public class OperacoesEmLoteTest extends EntityManagerTest {

    private static final int LIMITE_INSERCOES = 4;

    @Test
    public void removerEmLote() {
        entityManager.getTransaction().begin();

        String jpql = "delete from Produto p where p.id between 8 and 12";

        Query query = entityManager.createQuery(jpql);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }
    
    @Test
    public void atualizarEmLote() {
        entityManager.getTransaction().begin();

        String jpql = "update Produto p set p.preco = p.preco + (p.preco * 0.1) " +
                " where exists (select 1 from p.categorias c2 where c2.id = :categoria)";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoria", 2);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }
    
}
