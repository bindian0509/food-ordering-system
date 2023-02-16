package com.bharat.food.ordering.system.saga;

import com.bharat.food.ordering.system.domain.event.DomainEvent;

public interface SagaStep<T> {
    void process(T data);
    void rollback(T data);
}

