package com.bharat.food.ordering.system.customer.service.domain.exception;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import com.bharat.food.ordering.system.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }
}
