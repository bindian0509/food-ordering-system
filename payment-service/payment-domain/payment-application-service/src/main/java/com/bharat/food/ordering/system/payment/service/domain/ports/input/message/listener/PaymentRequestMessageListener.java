package com.bharat.food.ordering.system.payment.service.domain.ports.input.message.listener;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
