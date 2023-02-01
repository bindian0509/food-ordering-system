package com.bharat.food.ordering.system.restaurant.service.domain;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestaurantDomainService restaurantDomainService() {
        return new RestaurantDomainServiceImpl();
    }

}
