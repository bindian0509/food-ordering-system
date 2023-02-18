package com.bharat.food.ordering.system.payment.service.domain.ports.output.message.publisher;
/*
 * @author bharat.verma
 * @created Sunday, 19 February 2023
 */

import com.bharat.food.ordering.system.outbox.OutboxStatus;
import com.bharat.food.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface PaymentResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
