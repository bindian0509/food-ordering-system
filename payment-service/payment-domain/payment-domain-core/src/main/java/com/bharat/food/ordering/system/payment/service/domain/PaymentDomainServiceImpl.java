package com.bharat.food.ordering.system.payment.service.domain;
/*
 * @author bharat.verma
 * @created Friday, 20 January 2023
 */

import com.bharat.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PaymentDomainServiceImpl implements PaymentDomainService{
    @Override
    public PaymentEvent validateAndInitializePayment(Payment payment,
                                                     CreditEntry creditEntry,
                                                     List<CreditHistory> creditHistories,
                                                     List<String> failureMessages) {
        payment.validatePayment(failureMessages);
        payment.initializePayment();
        validateCreditEntry(payment, creditEntry, failureMessages);
        return null;
    }

    @Override
    public PaymentEvent validateAndCancelPayment(Payment payment,
                                                 CreditEntry creditEntry,
                                                 List<CreditHistory> creditHistories,
                                                 List<String> failureMessages) {
        return null;
    }


    private void validateCreditEntry(Payment payment,
                                     CreditEntry creditEntry,
                                     List<String> failureMessages) {
        if(payment.getPrice().isGreaterThan(creditEntry.getTotalCreditAmount())) {
            log.error("Customer with id : {} doesn't have enough credit to make this payment!",
                    payment.getCustomerId().getValue());
            failureMessages.add("Customer with id : "+payment.getCustomerId().getValue()+
                    " doesn't have enough credit to make this payment!");

        }
    }

}
