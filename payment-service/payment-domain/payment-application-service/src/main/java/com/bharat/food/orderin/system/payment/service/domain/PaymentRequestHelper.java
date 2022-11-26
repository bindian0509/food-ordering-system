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
import com.bharat.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.payment.service.domain.PaymentDomainService;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditEntry;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentEvent;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PaymentRequestHelper {

    private final PaymentDomainService paymentDomainService;
    private final PaymentDataMapper paymentDataMapper;
    private final PaymentRepository paymentRepository;
    private final CreditEntryRepository creditEntryRepository ;
    private final CreditHistoryRepository creditHistoryRepository;
    private final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher;
    private final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;
    private final DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher;

    public PaymentRequestHelper(PaymentDomainService paymentDomainService,
                                PaymentDataMapper paymentDataMapper,
                                PaymentRepository paymentRepository,
                                CreditEntryRepository creditEntryRepository,
                                CreditHistoryRepository creditHistoryRepository,
                                DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
                                DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
                                DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
        this.paymentDomainService = paymentDomainService;
        this.paymentDataMapper = paymentDataMapper;
        this.paymentRepository = paymentRepository;
        this.creditEntryRepository = creditEntryRepository;
        this.creditHistoryRepository = creditHistoryRepository;
        this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
        this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
        this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
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
                        payment, creditEntry, creditHistories, failureMessages,
                        paymentCompletedEventDomainEventPublisher,
                        paymentFailedEventDomainEventPublisher);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
        return paymentEvent;
    }



    @Transactional
    public PaymentEvent persistCancelPayment (PaymentRequest paymentRequest) {
        log.info("Received payment roll back event for orderId : {} ", paymentRequest.getOrderId());
        Optional<Payment> paymentResponse =
                paymentRepository.findByOrderId(UUID.fromString(paymentRequest.getOrderId()));
        if(paymentResponse.isEmpty()) {
            log.error("payment with orderId : {} could not be found", paymentRequest.getOrderId());
            throw new PaymentApplicationServiceException("payment with orderId : "
                    +paymentRequest.getOrderId()+" could not be found!");
        }
        Payment payment = paymentResponse.get();
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent = paymentDomainService
                .validateAndCancelPayment(payment, creditEntry, creditHistories, failureMessages,
                        paymentCancelledEventDomainEventPublisher,
                        paymentFailedEventDomainEventPublisher);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
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

    private void persistDbObjects(Payment payment,
                                  CreditEntry creditEntry,
                                  List<CreditHistory> creditHistories,
                                  List<String> failureMessages) {
        paymentRepository.save(payment);

        if(failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.get(creditHistories.size()-1));
        }
    }
}
