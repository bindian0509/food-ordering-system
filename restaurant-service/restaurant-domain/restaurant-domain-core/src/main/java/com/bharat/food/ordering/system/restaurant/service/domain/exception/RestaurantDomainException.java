package com.bharat.food.ordering.system.restaurant.service.domain.exception;
/*
 * @author bharat.verma
 * @created Monday, 06 February 2023
 */

import com.bharat.food.ordering.system.domain.exception.DomainException;

public class RestaurantDomainException extends DomainException {
    public RestaurantDomainException(String message) {
        super(message);
    }

    public RestaurantDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
