package com.bharat.food.ordering.system.restaurant.service.domain.ports.output.repository;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */

import com.bharat.food.ordering.system.restaurant.service.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
