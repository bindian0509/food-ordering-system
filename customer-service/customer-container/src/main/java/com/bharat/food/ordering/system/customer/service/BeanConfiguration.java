package com.bharat.food.ordering.system.customer.service;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}