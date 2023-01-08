package com.food.bharat.ordering.system.dataaccess.restaurant.repository;

import com.bharat.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntity;
import com.bharat.food.ordering.system.order.service.dataaccess.restaurant.entity.RestaurantEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantJpaRespository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {

    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> restaurantProducts);
}
