package com.bharat.food.ordering.system.saga;
/*
 * @author bharat.verma
 * @created Monday, 13 February 2023
 */

public enum SagaStatus {
    STARTED, FAILED, SUCCEEDED, PROCESSING, COMPENSATING, COMPENSATED
}
