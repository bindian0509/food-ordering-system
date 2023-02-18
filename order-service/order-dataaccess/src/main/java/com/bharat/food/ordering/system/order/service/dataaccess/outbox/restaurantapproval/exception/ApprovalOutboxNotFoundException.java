package com.bharat.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.exception;
/*
 * @author bharat.verma
 * @created Thursday, 16 February 2023
 */


public class ApprovalOutboxNotFoundException extends RuntimeException {

    public ApprovalOutboxNotFoundException(String message) {
        super(message);
    }
}
