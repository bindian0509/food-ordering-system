package com.bharat.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;
/*
 * @author bharat.verma
 * @created Monday, 13 February 2023
 */


import com.bharat.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.bharat.food.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}