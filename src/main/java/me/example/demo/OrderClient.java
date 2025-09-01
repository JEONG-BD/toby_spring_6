package me.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import me.example.demo.order.Order;
import me.example.demo.order.OrderRepository;
import me.example.demo.order.OrderService;
import me.example.demo.payment.Payment;
import me.example.demo.payment.PaymentService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);


        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println("order = " + order);
    }
}
