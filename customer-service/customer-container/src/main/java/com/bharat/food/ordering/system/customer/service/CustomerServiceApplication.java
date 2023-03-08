package com.bharat.food.ordering.system.customer.service;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableJpaRepositories(basePackages = { "com.bharat.food.ordering.system.customer.service.dataaccess", "com.bharat.food.ordering.system.dataaccess"})
@EntityScan(basePackages = { "com.bharat.food.ordering.system.customer.service.dataaccess", "com.bharat.food.ordering.system.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.bharat.food.ordering.system")
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
