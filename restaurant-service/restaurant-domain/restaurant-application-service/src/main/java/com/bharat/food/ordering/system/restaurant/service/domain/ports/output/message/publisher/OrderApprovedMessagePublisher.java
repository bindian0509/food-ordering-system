package com.bharat.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import com.bharat.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;

/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */
public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
}
