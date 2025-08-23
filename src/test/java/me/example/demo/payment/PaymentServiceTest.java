package me.example.demo.payment;

import me.example.demo.exrate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {



    @Test
    @DisplayName("prepare method가 3가지 요구사항 충족 검증")
    @Order(1)
    public void prepare() throws Exception{
        //given
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        //when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        //then
        //환율 정보
        assertThat(payment.getExRate()).isNotNull();

        //원화 환산 금액 산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
        //유효 시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }
}