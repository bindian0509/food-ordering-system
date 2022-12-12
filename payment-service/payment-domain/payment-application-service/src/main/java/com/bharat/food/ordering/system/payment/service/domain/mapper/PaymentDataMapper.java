package com.bharat.food.ordering.system.payment.service.domain.mapper;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.domain.vo.Money;
import com.bharat.food.ordering.system.domain.vo.OrderId;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {

        return Payment.builder()
                .orderId(new OrderId(UUID. fromString (paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString (paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();

    }
}
