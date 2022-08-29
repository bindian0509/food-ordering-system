package com.bharat.food.ordering.system.order.service.domain;
/*
 * @author bharat.verma
 * @created Monday, 29 August 2022
 */

import com.bharat.food.ordering.system.order.service.domain.entity.Order;
import com.bharat.food.ordering.system.order.service.domain.entity.Product;
import com.bharat.food.ordering.system.order.service.domain.entity.Restaurant;
import com.bharat.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.bharat.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.bharat.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.bharat.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService{
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with the id : {} is initialized", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> {
            restaurant.getProducts().forEach(restaurantProduct -> {
                Product currProd = orderItem.getProduct();
                if(currProd.equals(restaurantProduct)) {
                    currProd.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
                }
            });
        });
    }

    private void validateRestaurant(Restaurant restaurant) {
        if(!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id : "+restaurant.getId().getValue()+" is not accepting orders.");
        }
    }


    @Override
    public OrderPaidEvent payOrder(Order order) {
        return null;
    }

    @Override
    public void approveOrder() {

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        return null;
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {

    }
}
