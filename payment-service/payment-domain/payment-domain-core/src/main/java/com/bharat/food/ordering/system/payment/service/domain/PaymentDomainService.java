package com.bharat.food.ordering.system.payment.service.domain;
/*
 * @author bharat.verma
 * @created Friday, 20 January 2023
 */

import com.bharat.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitializePayment(Payment payment,
                                              CreditEntry creditEntry,
                                              List<CreditHistory> creditHistories,
                                              List<String> failureMessages,
                                              DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
