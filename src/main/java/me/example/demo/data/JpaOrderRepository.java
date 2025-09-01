package me.example.demo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import me.example.demo.order.Order;
import me.example.demo.order.OrderRepository;
import org.hibernate.exception.ConstraintViolationException;

import java.math.BigDecimal;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //private final EntityManagerFactory emf;
    //
    //public OrderRepository(EntityManagerFactory emf) {
    //    this.emf = emf;
    //}

    @Override
    public void save(Order order){
     entityManager.persist(order);
    }

}
