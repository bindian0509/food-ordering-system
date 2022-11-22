package com.bharat.food.orderin.system.payment.service.domain;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.orderin.system.payment.service.domain.dto.PaymentRequest;
import com.bharat.food.orderin.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {
    @Override
    public void completePayment(PaymentRequest paymentRequest) {

    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {

    }
}
