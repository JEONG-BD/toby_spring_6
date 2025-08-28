package me.example.demo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import me.example.demo.order.Order;

import java.math.BigDecimal;

public class OrderRepository {

    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(order);
            transaction.commit();
        } catch (RuntimeException ex){
            if(transaction.isActive()) transaction.rollback();
            throw ex;
        } finally {
            if (em.isOpen()) em.close();
        }
    }

}
