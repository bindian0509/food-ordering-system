package com.bharat.food.ordering.system.order.service.dataaccess.outbox.payment.exception;
/*
 * @author bharat.verma
 * @created Saturday, 18 February 2023
 */

public class PaymentOutboxNotFoundException extends RuntimeException {

    public PaymentOutboxNotFoundException(String message) {
        super(message);
    }
}
