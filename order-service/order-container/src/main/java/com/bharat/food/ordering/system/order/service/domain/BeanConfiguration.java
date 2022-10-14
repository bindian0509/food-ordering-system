package com.bharat.food.ordering.system.order.service.domain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author Bharat
 * @created Friday, 14 October 2022
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
