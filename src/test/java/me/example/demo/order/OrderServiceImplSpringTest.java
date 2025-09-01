package me.example.demo.order;

import me.example.demo.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;


    @Test
    public void createOrder() {
        //given
        var order = orderService.createOrder("0100", BigDecimal.ONE);
        //when
        assertThat(order.getId()).isGreaterThan(0);
        //then
    }
    @Test
    public void createOrders(){
        //given
        List<OrderReq> orderReqs = List.of(new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TWO));
        //when
        var orders = orderService.createOrders(orderReqs);

        //then

        assertThat(orders).hasSize(2);
        orders.forEach(order -> {
            assertThat(order.getId()).isGreaterThan(0);
        });
    }

    @Test
    public void createDuplicateOrders(){
        //given
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TWO));

        //then
        assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                .isInstanceOf(DataIntegrityViolationException.class);


        JdbcClient jdbcClient = JdbcClient.create(dataSource);
        var count = jdbcClient.sql("select count(*) from orders where no = '0300'")
                .query(Long.class)
                .single();

        Assertions.assertThat(count).isEqualTo(0);

    }

}
