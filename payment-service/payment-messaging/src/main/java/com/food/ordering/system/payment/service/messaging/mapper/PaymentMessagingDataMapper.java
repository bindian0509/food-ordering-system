package com.food.ordering.system.payment.service.messaging.mapper;
/*
 * @author bharat.verma
 * @created Friday, 27 January 2023
 */

import com.bharat.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.bharat.food.ordering.system.kafka.order.avro.model.PaymentStatus;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentResponseAvroModel paymentCompletedEventToPaymentResponseAvroModel(PaymentCompletedEvent paymentCompletedEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setPaymentId (paymentCompletedEvent.getPayment().getId().getValue().toString())
                .setCustomerId (paymentCompletedEvent.getPayment().getCustomerId().getValue().toString())
                .setOrderId (paymentCompletedEvent.getPayment().getOrderId().getValue().toString())
                .setPrice (paymentCompletedEvent.getPayment().getPrice().getAmount())
                .setCreatedAt (paymentCompletedEvent.getCreatedAt().toInstant())
                .setPaymentStatus (PaymentStatus.valueOf(paymentCompletedEvent.getPayment().getPaymentStatus().name()))
                .setFailureMessages (paymentCompletedEvent.getFailureMessages())
                .build();
    }

    public PaymentResponseAvroModel
    paymentCancelledEventToPaymentResponseAvroModel (PaymentCancelledEvent paymentCancelledEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setPaymentId(paymentCancelledEvent.getPayment().getId().getValue().toString())
                .setCustomerId(paymentCancelledEvent.getPayment().getCustomerId().getValue().toString())
                .setOrderId(paymentCancelledEvent.getPayment().getOrderId().getValue().toString())
                .setPrice(paymentCancelledEvent.getPayment().getPrice().getAmount())
                .setCreatedAt(paymentCancelledEvent.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(paymentCancelledEvent.getPayment().getPaymentStatus().name()))
                .setFailureMessages(paymentCancelledEvent.getFailureMessages())
                .build();
    }
}
