package com.bharat.food.orderin.system.payment.service.domain.dto;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.domain.vo.PaymentOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {

    private String id;
    private String sagaId;
    private String orderId;
    private String customerId;

    private BigDecimal price;
    private Instant createdAt;
    private PaymentOrderStatus paymentOrderStatus;

    public PaymentRequest setPaymentOrderStatus(PaymentOrderStatus paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
        return this;
    }
}
