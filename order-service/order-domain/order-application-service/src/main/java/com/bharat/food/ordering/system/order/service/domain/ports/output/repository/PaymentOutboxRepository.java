package com.bharat.food.ordering.system.order.service.domain.ports.output.repository;
/*
 * @author bharat.verma
 * @created Monday, 13 February 2023
 */

import com.bharat.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.bharat.food.ordering.system.outbox.OutboxStatus;
import com.bharat.food.ordering.system.saga.SagaStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PaymentOutboxRepository {

    OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage);

    Optional<List<OrderPaymentOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String type,
                                                                                     OutboxStatus outboxStatus,
                                                                                     SagaStatus... sagaStatus);
    Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String type,
                                                                         UUID sagaId,
                                                                         SagaStatus... sagaStatus);
    void deleteByTypeAndOutboxStatusAndSagaStatus(String type,
                                                  OutboxStatus outboxStatus,
                                                  SagaStatus... sagaStatus);
}

