package me.example.demo;

import me.example.demo.data.OrderRepository;
import me.example.demo.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataCofig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepository();
    }

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager){
        return new OrderService(orderRepository(), transactionManager);
    }

}
