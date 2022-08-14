package com.bharat.food.ordering.system.order.service.domain.exception;
/*
 * @author bharat.verma
 * @created Friday, 12 August 2022
 */

import com.bharat.food.ordering.system.domain.exception.DomainException;

public class OrderDomainException extends DomainException {

    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
