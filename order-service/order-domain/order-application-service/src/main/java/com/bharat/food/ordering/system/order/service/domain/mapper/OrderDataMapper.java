package com.bharat.food.ordering.system.order.service.domain.mapper;

import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.domain.vo.Money;
import com.bharat.food.ordering.system.domain.vo.ProductId;
import com.bharat.food.ordering.system.domain.vo.RestaurantId;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bharat.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderReponse;
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

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public List<OrderItem> orderItemsToOrderItemsEntities(List<com.bharat.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {

        return orderItems.stream().map(
                orderItem -> OrderItem.builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()
        ).collect(Collectors.toList());
    }

    public StreetAdress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAdress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

    public TrackOrderReponse orderToTrackOrderResponse(Order order) {
        return TrackOrderReponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }
}
