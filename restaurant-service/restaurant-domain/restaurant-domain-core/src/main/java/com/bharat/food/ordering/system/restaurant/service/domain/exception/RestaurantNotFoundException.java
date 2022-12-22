package com.bharat.food.ordering.system.restaurant.service.domain.exception;
/*
 * @author bharat.verma
 * @created Monday, 06 February 2023
 */

import com.bharat.food.ordering.system.domain.exception.DomainException;

public class RestaurantNotFoundException extends DomainException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
