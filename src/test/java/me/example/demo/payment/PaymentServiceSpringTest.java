package me.example.demo.payment;

import me.example.demo.TestPaymentConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class )
@ContextConfiguration(classes=TestPaymentConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceSpringTest {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare method가 3가지 요구사항 충족 검증")
    @Order(1)
    public void convertedAmount() throws Exception{

        //BeanFactory beanFactory = new AnnotationConfigApplicationContext(TestObjectFactory.class);
        //PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        //when
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        //then
        //환율 정보
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(1_0000));
        //given
        //유효 시간 계산

        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", TEN);
        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));

        //assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        //assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }
}