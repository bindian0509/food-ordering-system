package com.bharat.food.orderin.system.payment.service.domain;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.orderin.system.payment.service.domain.dto.PaymentRequest;
import com.bharat.food.orderin.system.payment.service.domain.exception.PaymentApplicationServiceException;
import com.bharat.food.orderin.system.payment.service.domain.mapper.PaymentDataMapper;
import com.bharat.food.orderin.system.payment.service.domain.ports.output.repository.CreditEntryRepository;
import com.bharat.food.orderin.system.payment.service.domain.ports.output.repository.CreditHistoryRepository;
import com.bharat.food.orderin.system.payment.service.domain.ports.output.repository.PaymentRepository;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.payment.service.domain.PaymentDomainService;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PaymentRequestHelper {

    private final PaymentDomainService paymentDomainService;
    private final PaymentDataMapper paymentDataMapper;
    private final PaymentRepository paymentRepository;
    private final CreditEntryRepository creditEntryRepository ;
    private final CreditHistoryRepository creditHistoryRepository;

    public PaymentRequestHelper(PaymentDomainService paymentDomainService,
                                PaymentDataMapper paymentDataMapper,
                                PaymentRepository paymentRepository,
                                CreditEntryRepository creditEntryRepository,
                                CreditHistoryRepository creditHistoryRepository) {
        this.paymentDomainService = paymentDomainService;
        this.paymentDataMapper = paymentDataMapper;
        this.paymentRepository = paymentRepository;
        this.creditEntryRepository = creditEntryRepository;
        this.creditHistoryRepository = creditHistoryRepository;
    }

    @Transactional
    public PaymentEvent persistPayment(PaymentRequest paymentRequest) {
        log.info("Received Payment complete event for orderId : {}", paymentRequest.getOrderId());
        Payment payment = paymentDataMapper.paymentRequestModelToPayment(paymentRequest);
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent =
                paymentDomainService.validateAndInitializePayment(
                        payment, creditEntry, creditHistories, failureMessages);
        paymentRepository.save(payment);

        if(failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.get(creditHistories.size()-1));
        }
        return paymentEvent;
    }

    private CreditEntry getCreditEntry(CustomerId customerId) {
        Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(customerId);
        if(creditEntry.isEmpty()) {
            log.error("could not find credit entry for the customer : {}", customerId.getValue());
            throw new PaymentApplicationServiceException("Could not find credit entry for the customer: "+
                    customerId.getValue());
        }
        return creditEntry.get();
    }

    private List<CreditHistory> getCreditHistory(CustomerId customerId) {
        Optional<List<CreditHistory>> creditHistories = creditHistoryRepository.findByCustomerId(customerId);
        if(creditHistories.isEmpty()) {
            log.error("could not find credit history for the customer : {} ", customerId);
            throw new PaymentApplicationServiceException("Could not find credit history for the customer: "+
                    customerId.getValue());
        }
        return creditHistories.get();
    }
}