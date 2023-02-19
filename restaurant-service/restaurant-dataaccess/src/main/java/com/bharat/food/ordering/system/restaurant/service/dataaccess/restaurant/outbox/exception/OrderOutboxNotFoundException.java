package com.bharat.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.exception;
/*
 * @author bharat.verma
 * @created Sunday, 19 February 2023
 */

public class OrderOutboxNotFoundException extends RuntimeException {

    public OrderOutboxNotFoundException(String message) {
        super(message);
    }
}
