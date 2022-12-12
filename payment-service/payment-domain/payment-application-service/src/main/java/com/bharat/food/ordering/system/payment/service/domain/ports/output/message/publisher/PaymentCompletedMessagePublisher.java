package com.bharat.food.ordering.system.payment.service.domain.ports.output.message.publisher;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {

}
