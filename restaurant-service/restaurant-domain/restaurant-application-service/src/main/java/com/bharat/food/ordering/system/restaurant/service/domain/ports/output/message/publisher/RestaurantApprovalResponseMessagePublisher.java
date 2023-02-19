package com.bharat.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;
/*
 * @author bharat.verma
 * @created Sunday, 19 February 2023
 */

import com.bharat.food.ordering.system.outbox.OutboxStatus;
import com.bharat.food.ordering.system.restaurant.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface RestaurantApprovalResponseMessagePublisher {

    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
