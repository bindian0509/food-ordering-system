package com.bharat.food.ordering.system.order.service.domain.entity;

import com.bharat.food.ordering.system.domain.entity.AggregateRoot;
import com.bharat.food.ordering.system.domain.vo.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
