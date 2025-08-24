package me.example.demo;

import me.example.demo.payment.Payment;
import me.example.demo.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));

        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment1);
        System.out.println(payment2);
        System.out.println(payment3);
    }
}
