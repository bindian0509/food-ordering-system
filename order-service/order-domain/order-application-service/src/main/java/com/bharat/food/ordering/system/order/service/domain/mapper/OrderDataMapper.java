package com.bharat.food.ordering.system.order.service.domain.mapper;

import com.bharat.food.ordering.system.domain.vo.*;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.bharat.food.ordering.system.order.service.domain.entity.Order;
import com.bharat.food.ordering.system.order.service.domain.entity.OrderItem;
import com.bharat.food.ordering.system.order.service.domain.entity.Product;
import com.bharat.food.ordering.system.order.service.domain.entity.Restaurant;
import com.bharat.food.ordering.system.order.service.domain.vo.StreetAdress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {


    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                        new Product(new ProductId(orderItem.getProductId()))).collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAdress(orderAddressToStreetAddress(createOrderCommand.getOrderAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemsEntities(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemsEntities(List<com.bharat.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {

        return orderItems.stream().map(
                orderItem -> OrderItem.builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()
        ).collect(Collectors.toList());
    }

    private StreetAdress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAdress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }
}
