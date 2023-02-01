package com.bharat.food.ordering.system.restaurant.service.domain;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.bharat.food.ordering.system.restaurant.service.dataaccess", "com.bharat.food.ordering.system.dataaccess" })
@EntityScan(basePackages = { "com.bharat.food.ordering.system.restaurant.service.dataaccess", "com.bharat.food.ordering.system.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.bharat.food.ordering.system")
public class RestaurantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
