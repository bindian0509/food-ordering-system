package com.bharat.food.ordering.system.customer.service;
/*
 * @author bharat.verma
 * @created Wednesday, 26 October 2022
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bharat.food.ordering.system")
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
