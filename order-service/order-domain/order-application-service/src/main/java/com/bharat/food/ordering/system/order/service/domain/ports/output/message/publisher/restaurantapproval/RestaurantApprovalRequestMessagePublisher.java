package com.bharat.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;
/*
 * @author bharat.verma
 * @created Monday, 13 February 2023
 */

import com.bharat.food.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.bharat.food.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface RestaurantApprovalRequestMessagePublisher {

    void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                 BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback);
}
