package com.bharat.food.ordering.system.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * @author Bharat
 * @created Friday, 14 October 2022
 */

@EnableJpaRepositories(basePackages = {"com.bharat.food.ordering.system.order.service.dataaccess", "com.bharat.food.ordering.system.dataaccess"})
@EntityScan(basePackages = {"com.bharat.food.ordering.system.order.service.dataaccess", "com.bharat.food.ordering.system.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.bharat.food.ordering.system")
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
