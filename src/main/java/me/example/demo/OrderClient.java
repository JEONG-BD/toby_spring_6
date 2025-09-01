package me.example.demo;

import me.example.demo.order.Order;
import me.example.demo.order.OrderRepository;
import me.example.demo.order.OrderService;
import me.example.demo.order.OrderServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderServiceImpl.class);


        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println("order = " + order);
    }
}
