package com.bharat.food.ordering.system.payment.service.dataaccess.outbox.exception;
/*
 * @author bharat.verma
 * @created Sunday, 19 February 2023
 */


public class OrderOutboxNotFoundException extends RuntimeException {

    public OrderOutboxNotFoundException(String message) {
        super(message);
    }
}
