package com.bharat.food.ordering.system.domain.exception;
/*
 * @author bharat.verma
 * @created Friday, 12 August 2022
 */

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
