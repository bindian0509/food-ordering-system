package com.bharat.food.ordering.system.restaurant.service.domain.exception;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */


import com.bharat.food.ordering.system.domain.exception.DomainException;

public class RestaurantApplicationServiceException extends DomainException {
    public RestaurantApplicationServiceException(String message) {
        super(message);
    }

    public RestaurantApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
