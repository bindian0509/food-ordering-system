package com.bharat.food.ordering.system.payment.service.domain.event;
/*
 * @author bharat.verma
 * @created Friday, 20 January 2023
 */

import com.bharat.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.Collections;

public class PaymentCancelledEvent extends PaymentEvent{

    private final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;

    public PaymentCancelledEvent(Payment payment,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher) {
        super(payment, createdAt, Collections.emptyList());
        this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        paymentCancelledEventDomainEventPublisher.publish(this);
    }
}
