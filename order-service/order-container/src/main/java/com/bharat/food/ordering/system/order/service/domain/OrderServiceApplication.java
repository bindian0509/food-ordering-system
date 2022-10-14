package com.bharat.food.ordering.system.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * @author bharat
 * @created Friday, 14 October 2022
 */

@EnableJpaRepositories(basePackages = "com.bharat.food.ordering.system.order.service.dataaccess")
@EntityScan(basePackages = "com.bharat.food.ordering.system.order.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.bharat.food.ordering.system")
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
