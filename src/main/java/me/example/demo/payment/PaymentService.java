package me.example.demo.payment;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

//@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;
    private final Clock clock;
    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        //의존 관계 설정
        this.exRateProvider = exRateProvider;
        this.clock = clock;

    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)  {

        BigDecimal exRate = exRateProvider.getExRate(currency);


        //return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
    }


}
