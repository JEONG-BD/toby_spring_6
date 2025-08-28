package me.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import me.example.demo.order.Order;
import me.example.demo.payment.Payment;
import me.example.demo.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataCofig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Order order = new Order("100", BigDecimal.TEN);
        System.out.println(order);
        em.persist(order);
        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }
}
