package me.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import me.example.demo.data.OrderRepository;
import me.example.demo.order.Order;
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

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataCofig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);
        try {

        new TransactionTemplate(transactionManager).execute(status -> {
            Order order = new Order("100", BigDecimal.TEN);
            orderRepository.save(order);
            Order order2 = new Order("100", BigDecimal.TEN);
            orderRepository.save(order2);
            return null;
        });
        } catch (DataIntegrityViolationException e){
            System.out.println("주문번호 충돌 복구 작업");
        }


        //
    }
}
