package me.example.demo;

import me.example.demo.exrate.CachedExRateProvider;
import me.example.demo.exrate.WebApiExRateProvider;
import me.example.demo.payment.ExRateProvider;
import me.example.demo.payment.ExRateProviderStub;
import me.example.demo.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

}
