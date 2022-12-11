package com.bharat.food.ordering.system.payment.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author bharat.verma
 * @created Sunday, 29 January 2023
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
