package com.bharat.food.ordering.system.payment.service.domain;
/*
 * @author bharat.verma
 * @created Sunday, 29 January 2023
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.bharat.food.ordering.system.payment.service.dataaccess")
@EntityScan(basePackages = "com.bharat.food.ordering.system.payment.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.bharat.food.ordering.system")
public class PaymentServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}


