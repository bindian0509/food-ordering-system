package com.bharat.food.ordering.system.customer.service.domain.entity;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */


import com.bharat.food.ordering.system.domain.entity.AggregateRoot;
import com.bharat.food.ordering.system.domain.vo.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    private final String username;
    private final String firstName;
    private final String lastName;

    public Customer(CustomerId customerId, String username, String firstName, String lastName) {
        super.setId(customerId);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
