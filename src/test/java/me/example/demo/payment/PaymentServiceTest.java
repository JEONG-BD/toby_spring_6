package me.example.demo.payment;

import me.example.demo.exrate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach(){
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    public void validUtil() throws Exception{
        //given
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);

        //when
        Payment payment = paymentService.prepare(1L, "USD", TEN);
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUtil = now.plusMinutes(30);
        //then

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUtil);
    }

    @Test
    @DisplayName("prepare method가 3가지 요구사항 충족 검증")
    @Order(1)
    public void prepare() throws Exception{
        //given
        getPayment(valueOf(500), valueOf(5_000), clock);

        //유효 시간 계산
        //assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        //assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }

    @NonNull
    private static void getPayment(BigDecimal exRate, BigDecimal convertAmount, Clock clock) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        //when
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        //then
        //환율 정보
        assertThat(payment.getExRate()).isNotNull();
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        //원화 환산 금액 산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertAmount);

    }
}