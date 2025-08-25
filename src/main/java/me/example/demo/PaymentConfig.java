package me.example.demo;

import me.example.demo.api.ApiTemplate;
import me.example.demo.exrate.CachedExRateProvider;
import me.example.demo.exrate.RestTemplateExRateProvider;
import me.example.demo.payment.ExRateProvider;
import me.example.demo.exrate.WebApiExRateProvider;
import me.example.demo.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
//@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new RestTemplateExRateProvider(restTemplate());
    }

    //@Bean
    //public ApiTemplate apiTemplate(){
    //    return new ApiTemplate();
    //}
     @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone();
    }
}
