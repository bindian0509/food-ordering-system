package com.bharat.food.ordering.system.customer.service.domain.event;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import com.bharat.food.ordering.system.customer.service.domain.entity.Customer;
import com.bharat.food.ordering.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;

    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
