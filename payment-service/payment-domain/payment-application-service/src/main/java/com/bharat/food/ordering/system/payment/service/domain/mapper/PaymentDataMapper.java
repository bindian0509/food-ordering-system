package com.bharat.food.ordering.system.payment.service.domain.mapper;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */


import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.domain.vo.Money;
import com.bharat.food.ordering.system.domain.vo.OrderId;
import com.bharat.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.bharat.food.ordering.system.payment.service.domain.outbox.model.OrderEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }

    public OrderEventPayload paymentEventToOrderEventPayload(PaymentEvent paymentEvent) {
        return OrderEventPayload.builder()
                .paymentId(paymentEvent.getPayment().getId().getValue().toString())
                .customerId(paymentEvent.getPayment().getCustomerId().getValue().toString())
                .orderId(paymentEvent.getPayment().getOrderId().getValue().toString())
                .price(paymentEvent.getPayment().getPrice().getAmount())
                .createdAt(paymentEvent.getCreatedAt())
                .paymentStatus(paymentEvent.getPayment().getPaymentStatus().name())
                .failureMessages(paymentEvent.getFailureMessages())
                .build();
    }
}
