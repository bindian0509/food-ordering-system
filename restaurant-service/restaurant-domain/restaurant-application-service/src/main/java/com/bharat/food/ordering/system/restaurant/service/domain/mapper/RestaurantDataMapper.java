package com.bharat.food.ordering.system.restaurant.service.domain.mapper;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */


import com.bharat.food.ordering.system.domain.vo.Money;
import com.bharat.food.ordering.system.domain.vo.OrderId;
import com.bharat.food.ordering.system.domain.vo.OrderStatus;
import com.bharat.food.ordering.system.domain.vo.RestaurantId;
import com.bharat.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.bharat.food.ordering.system.restaurant.service.domain.entity.OrderDetail;
import com.bharat.food.ordering.system.restaurant.service.domain.entity.Product;
import com.bharat.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.bharat.food.ordering.system.restaurant.service.domain.outbox.model.OrderEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {
    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                    restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream().map(
                                        product -> Product.builder()
                                                .productId(product.getId())
                                                .quantity(product.getQuantity())
                                                .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }

    public OrderEventPayload
    orderApprovalEventToOrderEventPayload(OrderApprovalEvent orderApprovalEvent) {
        return OrderEventPayload.builder()
                .orderId(orderApprovalEvent.getOrderApproval().getOrderId().getValue().toString())
                .restaurantId(orderApprovalEvent.getRestaurantId().getValue().toString())
                .orderApprovalStatus(orderApprovalEvent.getOrderApproval().getApprovalStatus().name())
                .createdAt(orderApprovalEvent.getCreatedAt())
                .failureMessages(orderApprovalEvent.getFailureMessages())
                .build();
    }
}