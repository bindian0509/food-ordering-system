package com.bharat.food.ordering.system.outbox;
/*
 * @author bharat.verma
 * @created Monday, 13 February 2023
 */


public interface OutboxScheduler {
    void processOutboxMessage();
}
