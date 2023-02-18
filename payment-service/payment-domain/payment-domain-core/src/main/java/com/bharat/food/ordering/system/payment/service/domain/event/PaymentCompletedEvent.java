package com.bharat.food.ordering.system.payment.service.domain.event;
/*
 * @author bharat.verma
 * @created Friday, 20 January 2023
 */

import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.Collections;

public class PaymentCompletedEvent extends PaymentEvent {

    public PaymentCompletedEvent(Payment payment,
                                 ZonedDateTime createdAt) {
        super(payment, createdAt, Collections.emptyList());
    }

}