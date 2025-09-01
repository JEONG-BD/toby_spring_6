package me.example.demo;

import me.example.demo.data.JdbcOrderRepository;
import me.example.demo.order.OrderRepository;
import me.example.demo.order.OrderService;
import me.example.demo.order.OrderServiceImpl;
import me.example.demo.order.OrderServiceTxProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataCofig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(DataSource dataSource){
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(
            PlatformTransactionManager transactionManager,
            OrderRepository orderRepository){
        return new OrderServiceTxProxy(
                new OrderServiceImpl(orderRepository),
                transactionManager
        );
    }

}
