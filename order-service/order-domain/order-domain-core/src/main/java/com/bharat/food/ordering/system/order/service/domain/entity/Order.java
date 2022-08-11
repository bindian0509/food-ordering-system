package com.bharat.food.ordering.system.order.service.domain.entity;
/*
 * @author bharat.verma
 * @created Thursday, 11 August 2022
 */

import com.bharat.food.ordering.system.domain.entity.AggregateRoot;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.domain.vo.Money;
import com.bharat.food.ordering.system.domain.vo.OrderId;
import com.bharat.food.ordering.system.domain.vo.RestaurantId;
import com.bharat.food.ordering.system.order.service.domain.vo.StreetAdress;

import java.util.List;

public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAdress deliveryAdress;
    private final Money price;
    private final List<OrderItem> items;
}
