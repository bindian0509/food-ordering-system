package com.bharat.food.ordering.system.restaurant.service.domain.ports.input.message.listener;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */

import com.bharat.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
